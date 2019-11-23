package com.github.swapbook.model;

import java.util.Date;

public class Review {
    private int id;
    private int bookId;
    private int userId;
    private Date addingDate;
    private String review;
    private int mark;

    public Review(int id, int bookId, int userId, String review) {
        this.id = id;
        this.bookId = bookId;
        this.userId = userId;
        this.review = review;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getAddingDate() {
        return addingDate;
    }

    public void setAddingDate(Date addingDate) {
        this.addingDate = addingDate;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getMark() { return mark; }

    public void setMark(int mark) { this.mark = mark; }
}
