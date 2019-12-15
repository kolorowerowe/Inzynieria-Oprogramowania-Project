package com.github.swapbook.api;

import com.github.swapbook.model.Loan;
import com.github.swapbook.repositories.loans.LoanDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Loans {

    @Autowired
    LoanDBRepository loanRepository;

    @GetMapping("/api/loans/all")
    @ResponseBody
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok().body(loanRepository.getAllLoans());
    }

    @GetMapping("/api/loans/state/{state}")
    @ResponseBody
    public ResponseEntity<List<Loan>> getLoansForState(@PathVariable(value = "state") String state) {
        return ResponseEntity.ok().body(loanRepository.getLoansInState(state));
    }

    @GetMapping("/api/loans/owner/{id}")
    @ResponseBody
    public ResponseEntity<List<Loan>> getLoansFromUser(@PathVariable(value = "id") int owner_id) {
        return ResponseEntity.ok().body(loanRepository.getLoansFromUser(owner_id));
    }

    @GetMapping("/api/loans/loaner/{id}")
    @ResponseBody
    public ResponseEntity<List<Loan>> getLoansToUser(@PathVariable(value = "id") int loaner_id) {
        return ResponseEntity.ok().body(loanRepository.getLoansToUser(loaner_id));
    }

    @GetMapping("/api/loans/{id}")
    @ResponseBody
    public ResponseEntity<Loan> getLoanById(@PathVariable(value = "id") int loan_id) {
        return ResponseEntity.ok().body(loanRepository.getLoanById(loan_id));
    }

    @PostMapping("/api/loans/put")
    public void createLoan(@RequestBody Loan loan) {
        loanRepository.addLoanToList(loan);
    }

    @DeleteMapping("/api/loans/{id}")
    public void deleteLoan(@PathVariable(value = "id") int loan_id) {
        loanRepository.deleteLoanById(loan_id);
    }






}
