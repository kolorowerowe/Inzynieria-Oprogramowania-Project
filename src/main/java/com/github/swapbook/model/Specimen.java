package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "swapbook.specimens")
public class Specimen {

    @Id
    @PrimaryKeyJoinColumn
    @Column
    private int specimen_id;

    @Column
    private int book_id;

    @Column
    private int user_id;

    @Column
    private String title;

    @Column
    private String condition;

    @Column
    private int number_pages;

    @Column
    private String author;

    @Column
    private Date release_date;

    @Column
    private String issue_number;

    @Column
    private String isbn;

    public int getSpecimen_id() {
        return specimen_id;
    }

    public void setSpecimen_id(int specimen_id) {
        this.specimen_id = specimen_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getNumber_pages() {
        return number_pages;
    }

    public void setNumber_pages(int number_pages) {
        this.number_pages = number_pages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Date release_date) {
        this.release_date = release_date;
    }

    public String getIssue_number() {
        return issue_number;
    }

    public void setIssue_number(String issue_number) {
        this.issue_number = issue_number;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getLoan_period() {
        return loan_period;
    }

    public void setLoan_period(int loan_period) {
        this.loan_period = loan_period;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Column
    private int loan_period;

    @Column
    private String photo_url;

    public Specimen() {}

    public Specimen(int specimen_id, int user_id, String title) {
        this.specimen_id = specimen_id;
        this.user_id = user_id;
        this.title = title;
    }

    public Specimen(int specimen_id, int book_id, int user_id, String title, String condition, int number_pages, String author, Date release_date, String issue_number, String isbn, int loan_period, String photo_url) {
        this.specimen_id = specimen_id;
        this.book_id = book_id;
        this.user_id = user_id;
        this.title = title;
        this.condition = condition;
        this.number_pages = number_pages;
        this.author = author;
        this.release_date = release_date;
        this.issue_number = issue_number;
        this.isbn = isbn;
        this.loan_period = loan_period;
        this.photo_url = photo_url;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specimen specimen = (Specimen) o;
        return specimen_id == specimen.specimen_id;
    }
}
