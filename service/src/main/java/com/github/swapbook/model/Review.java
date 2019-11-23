package com.github.swapbook.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Review")
public class Review {
    @Id
    @PrimaryKeyJoinColumn
    @Column(columnDefinition = "Id")
    private int id;

    @Column(columnDefinition = "BookId")
    private int bookId;

    @Column(columnDefinition = "UserId")
    private int userId;

    @Column(columnDefinition = "AddingDate")
    private Date addingDate;

    @Column(columnDefinition = "Review")
    private String review;

    @Column(columnDefinition = "Mark")
    private int mark;

    public Review() { }

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
