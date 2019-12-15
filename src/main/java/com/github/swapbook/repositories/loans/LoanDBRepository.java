package com.github.swapbook.repositories.loans;

import com.github.swapbook.model.Loan;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("swapbook.loans")
public class LoanDBRepository implements LoanRepository{

    @PersistenceContext
    private EntityManager entityManager;

    public LoanDBRepository() {
    }

    @Override
    public List<Loan> getAllLoans() {
        return entityManager.createNativeQuery("select * from swapbook.loans", Loan.class).getResultList();
    }

    @Override
    public List<Loan> getLoansInState(String state) {
        return entityManager.createNativeQuery("select * from swapbook.loans where state = ?", Loan.class)
                .setParameter(1, state)
                .getResultList();
    }

    @Override
    public List<Loan> getLoansFromUser(int owner_id) {
        return entityManager.createNativeQuery("select * from swapbook.loans where owner_id = ?", Loan.class)
                .setParameter(1, owner_id)
                .getResultList();
    }

    @Override
    public List<Loan> getLoansToUser(int loaner_id) {
        return entityManager.createNativeQuery("select * from swapbook.loans where loaner_id = ?", Loan.class)
                .setParameter(1, loaner_id)
                .getResultList();
    }

    @Override
    public Loan getLoanById(int loan_id) {
        return ((Loan) entityManager.createNativeQuery("select * from swapbook.loans WHERE loan_id=?", Loan.class)
                .setParameter(1, loan_id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void deleteLoanById(int loan_id) {
        entityManager.createNativeQuery("delete from swapbook.loans WHERE  loan_id=?", Loan.class)
                .setParameter(1, loan_id)
                .executeUpdate();
    }

    @Override
    @Transactional
    public void addLoanToList(Loan loan) {
        entityManager.createNativeQuery("INSERT INTO swapbook.loans VALUES (?,?,?,?,?,?,?,?)")
                .setParameter(1, loan.getLoan_id())
                .setParameter(2, loan.getSpecimen_id())
                .setParameter(3, loan.getOwner_id())
                .setParameter(4, loan.getLoaner_id())
                .setParameter(5, loan.getLoan_status())
                .setParameter(6, loan.getDate_loan())
                .setParameter(7, loan.getDate_return())
                .setParameter(8, loan.getPeriod_days())
                .executeUpdate();
    }
}
