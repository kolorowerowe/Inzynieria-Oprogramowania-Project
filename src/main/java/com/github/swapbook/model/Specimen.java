package com.github.swapbook.model;


import java.sql.Timestamp;
import java.util.Date;

public class Specimen {
    private int id;
    private int userId;
    private String title; //Title if better
    private String state;
    private int numberOfPages;
    private String author;
    private Date releaseDate;
    private String releaseNumber;
    private String isbn;
    private String publishingHouse;
    //private List<String> tags;
    private Timestamp rentalTime;

    public Specimen() {}

    public Specimen(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    //potrzebne do czesciowego testowania /api/specimens/put
    public Specimen(int id, String title, String state, int numberOfPages, String author)
    {
        System.out.println("Konstruktor");
        this.id = id;
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
    }

    public Specimen(int id, int userId, String title, String state, int numberOfPages, String author, Date releaseDate, String releaseNumber, String isbn, String publishingHouse, Timestamp rentalTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.releaseDate = releaseDate;
        this.releaseNumber = releaseNumber;
        this.isbn = isbn;
        this.publishingHouse = publishingHouse;
        //this.tags = tags;
        this.rentalTime = rentalTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {return state;}

    public int getNumberOfPages(){return numberOfPages;}

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
