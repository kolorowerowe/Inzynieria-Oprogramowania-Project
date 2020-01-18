package com.github.swapbook.api;

import com.github.swapbook.email.EmailService;
import com.github.swapbook.model.Loan;
import com.github.swapbook.model.LoanModel;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.loans.LoanService;
import com.github.swapbook.repositories.specimens.SpecimenService;
import com.github.swapbook.repositories.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class LoanApi {
    UserService userService;

    LoanService loanService;

    SpecimenService specimenService;

    EmailService emailService;
    private static String ApiUrl = "http://localhost:8080";

    @Autowired
    public LoanApi(UserService userService, LoanService loanService,
                   SpecimenService specimenService, EmailService emailService)
    {
        this.userService = userService;
        this.loanService = loanService;
        this.specimenService = specimenService;
        this.emailService = emailService;
    }

    @PostMapping("/api/loans/create")
    @ResponseBody
    public ResponseEntity<Boolean> createLoan(@RequestBody LoanModel model) throws MessagingException {
        if(model == null)
            return ResponseEntity.badRequest().body(false);

        Specimen sp = specimenService.getSpecimenById(model.getSpecimenId());
        User owner = userService.getUserById(sp.getUser_id());
        User loaner = userService.getUserById(model.getUserId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        Loan loan = new Loan();
        loan.setSpecimen_id(model.getSpecimenId());
        loan.setLoaner_id(model.getUserId());
        loan.setOwner_id(sp.getUser_id());
        loan.setPeriod_days(sp.getLoan_period());
        loan.setDate_loan(dateFormat.format(currentDate));
        Loan loanResult = loanService.addLoanToList(loan);

        emailService.sendMessage(owner.getEmail(), "Proźba o wypożycznie książki",
                "<html>" +
                        "<body>Użytkownik " + loaner.getName() +" prosi o wypożycznie ksiązki: " + sp.getTitle()+
                        "<br/>Ustalcie sami szczegóły wypożyczenia takie jak data, miejsce spotkania.</br>" +
                        "Email: " + loaner.getEmail() + " <br/>" +
                        "Po udanym spotkaniu kliknij poniższy link <br/>"+
                        "<a href=\""+ApiUrl+"/api/loans/confirmLoan/"+loanResult.getLoan_id()+"\">Wypożycz</a></br>" +
                        "<h3>Team swapBook</h3></body></html>"
                , true);

        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/api/loans/confirmLoan/{id}")
    public void confirmLoan(@PathVariable(value = "id") int loanId) {
        Loan loan = loanService.getLoanById(loanId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        loan.setDate_loan(dateFormat.format(currentDate));
        loanService.addLoanToList(loan);
    }

    @GetMapping("/api/loans/confirmLoanReturn/{id}")
    public void confirmLoanReturn(@PathVariable(value = "id") int loanId) {
        Loan loan = loanService.getLoanById(loanId);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date currentDate = new Date();

        loan.setDate_return(dateFormat.format(currentDate));
        loanService.addLoanToList(loan);
    }

    @GetMapping("/api/loans/return/{id}")
    @ResponseBody
    public ResponseEntity<Boolean> returnLoan(@PathVariable(value = "id") int loanId) throws MessagingException {
        if (loanId == 0)
            return ResponseEntity.badRequest().body(false);

        Loan loan = loanService.getLoanById(loanId);

        Specimen sp = specimenService.getSpecimenById(loan.getSpecimen_id());
        User owner = userService.getUserById(loan.getOwner_id());
        User loaner = userService.getUserById(loan.getLoaner_id());

        emailService.sendMessage(owner.getEmail(), "Zwrot książki",
                "<html>" +
                        "<body>Użytkownik " + loaner.getName() +" chce zwrócić książkę : " + sp.getTitle()+
                        "<br/>Ustalcie sami szczegóły zwrotu takie jak data, miejsce spotkania.</br>" +
                        "Email: " + loaner.getEmail() + " <br/>" +
                        "Po udanym spotkaniu kliknij poniższy link <br/>"+
                        "<a href=\""+ApiUrl+"/api/loans/confirmLoanReturn/"+loan.getLoan_id()+"\">Potwierdz odbiór</a></br>" +
                        "<h3>Team swapBook</h3></body></html>"
                , true);

        return ResponseEntity.ok().body(true);
    }
}
