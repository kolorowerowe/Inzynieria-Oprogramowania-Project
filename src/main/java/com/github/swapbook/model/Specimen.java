package com.github.swapbook.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Specimen {

    private int id;
    private int userId;
    private String title;
    private String state;
    private int numberOfPages;
    private String author;
    private LocalDate releaseDate;
    private String releaseNumber;
    private String isbn;
    private String publishingHouse;
    private LocalDate rentalTime;

    public Specimen() {}

    public Specimen(String title, String state, int numberOfPages, String author, String releaseDate) {
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.releaseDate = LocalDate.parse(releaseDate,dtf);
    }

    public Specimen(int id, int userId, String title) {
        this.id = id;
        this.userId = userId;
        this.title = title;
    }

    public Specimen(String title, String state, int numberOfPages, String author)
    {
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
    }


    public Specimen(int id, String title, String state, int numberOfPages, String author)
    {
        this.id = id;
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
    }

    public Specimen(String title, String state, int numberOfPages, String author, String releaseDate, String releaseNumber, String isbn, String publishingHouse, String rentalTime) {
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.releaseDate = LocalDate.parse(releaseDate,dtf);
        this.releaseNumber = releaseNumber;
        this.isbn = isbn;
        this.publishingHouse = publishingHouse;
        this.rentalTime = LocalDate.parse(rentalTime,dtf);;
    }

    public Specimen(int id, int userId, String title, String state, int numberOfPages, String author, String releaseDate, String releaseNumber, String isbn, String publishingHouse, String rentalTime) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.state = state;
        this.numberOfPages = numberOfPages;
        this.author = author;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.releaseDate = LocalDate.parse(releaseDate,dtf);
        this.releaseNumber = releaseNumber;
        this.isbn = isbn;
        this.publishingHouse = publishingHouse;
        this.rentalTime = LocalDate.parse(rentalTime,dtf);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Specimen specimen = (Specimen) o;
        return id == specimen.id;
    }

    //Getters and Setters

    public int getId() {
        return id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getReleaseDate() {
        return (releaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }

    public void setReleaseDate(String releaseDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.releaseDate = LocalDate.parse(releaseDate,dtf);
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getRentalTime() {
        if(rentalTime==null)
            return "null";
        else
            return rentalTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setRentalTime(String rentalTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        if (rentalTime.equals("null"))
            this.rentalTime = null;
        else
            this.rentalTime = LocalDate.parse(rentalTime,dtf);
    }
}
