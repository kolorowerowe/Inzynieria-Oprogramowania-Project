package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "swapbook.reviews")
public class Review {

    @Id
    @PrimaryKeyJoinColumn
    @Column
    private int review_id;

    @Column
    private int book_id;

    @Column
    private int user_id;

    @Column
    private String text;

    @Column
    private int mark;

    @Column
    private Date date;

    public Review() {
    }

    public int getReview_id() {
        return review_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Review(int review_id, int book_id, int user_id, String text, int mark, Date date) {
        this.review_id = review_id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.text = text;
        this.mark = mark;
        this.date = date;
    }

}
