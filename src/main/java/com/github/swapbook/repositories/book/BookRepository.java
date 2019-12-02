package com.github.swapbook.repositories.book;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Review;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();
    Book getBookById(int id);
    Book getBookByName(String name);
    void updateUniqueBooks();
    void addReviewToBook(int bookId, Review review);
}
