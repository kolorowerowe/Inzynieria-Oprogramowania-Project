package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "swapbook.books")
public class Book {

    @Id
    @PrimaryKeyJoinColumn
    @Column
    private int book_id;

    @Column
    private String title;

    @Column
    private String author;

    @Column
    private String photo_url;

    public Book() {}

    public Book(int book_id, String title, String author) {
        this.book_id = book_id;
        this.title = title;
        this.author = author;
        this.photo_url = "";
    }

    public int getBook_id() {
        return book_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPhoto_url() {
        return photo_url;
    }

    public void setPhoto_url(String photo_url) {
        this.photo_url = photo_url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return title == book.title;
    }

}
