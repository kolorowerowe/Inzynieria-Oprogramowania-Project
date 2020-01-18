package com.github.swapbook.repositories.loans;

import com.github.swapbook.model.Loan;

import java.util.List;

public interface LoanServiceI {
    List<Loan> getAllLoans();
    List<Loan> getLoansInState(String state);
    List<Loan> getLoansFromUser(int owner_id);
    List<Loan> getLoansToUser(int loaner_id);
    Loan getLoanById(int id);
    void deleteLoanById(int id);
    Loan addLoanToList(Loan loan);
}
