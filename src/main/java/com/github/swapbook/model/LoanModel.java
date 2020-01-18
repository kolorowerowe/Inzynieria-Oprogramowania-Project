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

//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    public int getSpecimenId() {
        return specimenId;
    }

//    public void setSpecimenId(int specimenId) {
//        this.specimenId = specimenId;
//    }
}
