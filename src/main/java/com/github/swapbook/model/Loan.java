package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "swapbook.loans")
public class Loan {

    @Id
    @PrimaryKeyJoinColumn
    @Column
    private int loan_id;

    @Column
    private int specimen_id;

    @Column
    private int owner_id;

    @Column
    private int loaner_id;

    @Column
    private String loan_status;

    @Column
    private String date_loan;

    @Column
    private String date_return;

    @Column
    private int period_days;

    public Loan() {
    }

    public Loan(int loan_id, int specimen_id, int owner_id, int loaner_id, String loan_status, String date_loan, String date_return, int period_days) {
        this.loan_id = loan_id;
        this.specimen_id = specimen_id;
        this.owner_id = owner_id;
        this.loaner_id = loaner_id;
        this.loan_status = loan_status;
        this.date_loan = date_loan;
        this.date_return = date_return;
        this.period_days = period_days;
    }

    public int getLoan_id() {
        return loan_id;
    }

    public int getSpecimen_id() {
        return specimen_id;
    }

    public void setSpecimen_id(int specimen_id) {
        this.specimen_id = specimen_id;
    }

    public int getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(int owner_id) {
        this.owner_id = owner_id;
    }

    public int getLoaner_id() {
        return loaner_id;
    }

    public void setLoaner_id(int loaner_id) {
        this.loaner_id = loaner_id;
    }

    public String getLoan_status() {
        return loan_status;
    }

    public void setLoan_id(int loan_id) {
        this.loan_id = loan_id;
    }

    public void setLoan_status(String loan_status) {
        this.loan_status = loan_status;
    }

    public String getDate_loan() {
        return date_loan;
    }

    public void setDate_loan(String date_loan) {
        this.date_loan = date_loan;
    }

    public String getDate_return() {
        return date_return;
    }

    public void setDate_return(String date_return) {
        this.date_return = date_return;
    }

    public int getPeriod_days() {
        return period_days;
    }

    public void setPeriod_days(int period_days) {
        this.period_days = period_days;
    }
}
