package com.github.swapbook.repositories.book;

import com.github.swapbook.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> getBooks();
    Book getBookById(int id);
    Book getBookByName(String name);
    void addToList(Book book);
    void deleteSpecimenById(int id);
    void updateUniqueBooks();
}
