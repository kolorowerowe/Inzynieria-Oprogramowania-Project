package com.github.swapbook.model;

public class LoanModel
{
    private int userId;
    private int specimenId;

    public LoanModel() {

    }

    public LoanModel(int userId, int specimenId) {
        this.userId = userId;
        this.specimenId = specimenId;
    }

    public int getUserId() {
        return userId;
    }


    public int getSpecimenId() {
        return specimenId;
    }

}
