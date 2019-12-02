package com.github.swapbook.model;

import java.util.LinkedList;
import java.util.List;

public class Book {
    private int id;
    private String name;
    private String author;
    private List<Review> reviewList;

    public Book() {}

    public Book(int id, String name, String author) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.reviewList = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        Book book = (Book) o;
        return name == book.name;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public void addReview(Review review) {
        this.reviewList.add((review));
    }
}
