package com.github.swapbook.model;

public class Statistic {

    private int specimenCount;
    private int ownerCount;
    private int loanerCount;

    private int reviewCount;
    private int writtenOpinionCount;

    public Statistic() {

    }

    public Statistic(int specimenCount, int ownerCount, int loanerCount) {
        this.specimenCount = specimenCount;
        this.ownerCount = ownerCount;
        this.loanerCount = loanerCount;
    }

    public Statistic(int specCount, int ownerCount, int loanerCount, int writtenOpinionCount, int reviewCount) {
        this.specimenCount = specimenCount;
        this.ownerCount = ownerCount;
        this.loanerCount = loanerCount;
        this.writtenOpinionCount = writtenOpinionCount;
        this.reviewCount = reviewCount;
    }

    public int getSpecimenCount() {
        return specimenCount;
    }

    public void setSpecimenCount(int specimenCount) {
        this.specimenCount = specimenCount;
    }

    public int getOwnerCount() {
        return ownerCount;
    }

    public void setOwnerCount(int ownerCount) {
        this.ownerCount = ownerCount;
    }

    public int getLoanerCount() {
        return loanerCount;
    }

    public void setLoanerCount(int loanerCount) {
        this.loanerCount = loanerCount;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(int reviewCount) {
        this.reviewCount = reviewCount;
    }

    public int getWrittenOpinionCount() {
        return writtenOpinionCount;
    }

    public void setWrittenOpinionCount(int writtenOpinionCount) {
        this.writtenOpinionCount = writtenOpinionCount;
    }
}
