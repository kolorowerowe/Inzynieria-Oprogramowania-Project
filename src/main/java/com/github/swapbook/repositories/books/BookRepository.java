package com.github.swapbook.repositories.book;

import com.github.swapbook.model.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository {
    List<Book> getBooks();
    Book getBookById(int id);
    BookRepository searchBooksByRegex(String title);
    BookRepository searchBooksByAuthor(String regex);
    boolean setContainsName(Set<Book> set, String name);
    Book getBookByTitle(String title);
    void deleteBookById(int id);
    void updateUniqueBooks();
    void addReviewToBook(int bookId, Review review);
    void addBookToList(Book book);
}
