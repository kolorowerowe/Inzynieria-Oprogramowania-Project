package com.github.swapbook.repositories.loans;

import com.github.swapbook.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService implements LoanServiceI {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    @Override
    public List<Loan> getLoansInState(String state) {
        return loanRepository.findAll().stream().filter(l -> l.getLoan_status().equals(state)).collect(Collectors.toList());
    }

    @Override
    public List<Loan> getLoansFromUser(int owner_id) {
        return loanRepository.findAll().stream().filter(l -> l.getOwner_id()==owner_id).collect(Collectors.toList());
    }

    @Override
    public List<Loan> getLoansToUser(int loaner_id) {
        return loanRepository.findAll().stream().filter(l -> l.getLoaner_id()==loaner_id).collect(Collectors.toList());
    }

    @Override
    public Loan getLoanById(int id) {
        return loanRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteLoanById(int id) {
        loanRepository.deleteById(id);
    }

    @Override
    public void addLoanToList(Loan loan) {
        loanRepository.save(loan);
    }
}
