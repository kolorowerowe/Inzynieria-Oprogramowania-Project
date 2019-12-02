package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Specimen {
    private int id;
    private int userId;
    private String name; //Title if better
    private String state;
    private int numberOfPages;
    private String author;
    private Date releaseDate;
    private String releaseNumber;
    private String isbn;
    private String publishingHouse;
    private List<String> tags;
    private Timestamp rentalTime;

    public Specimen() {}

    public Specimen(int id, int userId, String name) {
        this.id = id;
        this.userId = userId;
        this.name = name;
    }

    public Specimen(int id, int userId, String name, String state, int numberOfPages, String author, Date releaseDate, String releaseNumber, String isbn, String publishingHouse, List<String> tags, Timestamp rentalTime) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.releaseDate = releaseDate;
        this.releaseNumber = releaseNumber;
        this.isbn = isbn;
        this.publishingHouse = publishingHouse;
        this.tags = tags;
        this.rentalTime = rentalTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specimen specimen = (Specimen) o;
        return id == specimen.id;
    }
}
