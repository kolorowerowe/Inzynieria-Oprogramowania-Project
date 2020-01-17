package com.github.swapbook.api;

import com.github.swapbook.model.Loan;
import com.github.swapbook.repositories.loans.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Loans {

    @Autowired
    LoanService loanService;

    @GetMapping("/api/loans/all")
    @ResponseBody
    public ResponseEntity<List<Loan>> getAllLoans() {
        return ResponseEntity.ok().body(loanService.getAllLoans());
    }

    @GetMapping("/api/loans/state/{state}")
    @ResponseBody
    public ResponseEntity<List<Loan>> getLoansForState(@PathVariable(value = "state") String state) {
        return ResponseEntity.ok().body(loanService.getLoansInState(state));
    }

    @GetMapping("/api/loans/owner/{id}")
    @ResponseBody
    public ResponseEntity<List<Loan>> getLoansFromUser(@PathVariable(value = "id") int owner_id) {
        return ResponseEntity.ok().body(loanService.getLoansFromUser(owner_id));
    }

    @GetMapping("/api/loans/loaner/{id}")
    @ResponseBody
    public ResponseEntity<List<Loan>> getLoansToUser(@PathVariable(value = "id") int loaner_id) {
        return ResponseEntity.ok().body(loanService.getLoansToUser(loaner_id));
    }

    @GetMapping("/api/loans/{id}")
    @ResponseBody
    public ResponseEntity<Loan> getLoanById(@PathVariable(value = "id") int loan_id) {
        return ResponseEntity.ok().body(loanService.getLoanById(loan_id));
    }

    @PostMapping("/api/loans/put")
    public void createLoan(@RequestBody Loan loan) {
        loanService.addLoanToList(loan);
    }

    @DeleteMapping("/api/loans/{id}")
    public void deleteLoan(@PathVariable(value = "id") int loan_id) {
        loanService.deleteLoanById(loan_id);
    }






}
