package com.github.swapbook.repositories.books;

import com.github.swapbook.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();
    Book getBookById(int id);
    Book getBookByName(String name);
    void deleteBookById(int id);
    void updateUniqueBooks();
    void addBookToList(Book book);
}
