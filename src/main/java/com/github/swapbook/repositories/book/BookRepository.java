package com.github.swapbook.repositories.book;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Review;

import java.util.List;
import java.util.Set;

public interface BookRepository {
    List<Book> getBooks();
    Book getBookById(int id);
    Book getBookByName(String name);
    BookRepository searchBooksByRegex(String title);
    BookRepository searchBooksByAuthor(String regex);
    void updateUniqueBooks();
    void addReviewToBook(int bookId, Review review);
    void addBook(Book book);
}
