package com.github.swapbook.repositories.books;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.specimens.SpecimenDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class FakeBookRepository implements BookRepository {
    private Set<Book> bookSet = new HashSet<>();

    @Autowired
    SpecimenDBRepository specimenRepository;

    @Override
    public List<Book> getBooks() {
        return new LinkedList<>(bookSet);
    }

    @Override
    public Book getBookById(int id) {
        return bookSet.stream()
                .filter(b -> b.getBook_id() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Book getBookByTitle(String name) {
        return bookSet.stream()
                .filter(b -> b.getTitle() == name)
                .findAny()
                .orElse(null);
    }

    @Override
    public void deleteBookById(int id) {
       bookSet = bookSet.stream().filter(b -> b.getBook_id() != id).collect(Collectors.toSet());
    }

    public boolean setContainsName(Set<Book> set, String name) {
        return set.stream().anyMatch(b -> b.getTitle().equals(name));
    }

    @Override
    public void updateUniqueBooks() {
        Set<Book> bookSetTmp = new HashSet<>();

        for(Specimen specimen : specimenRepository.getSpecimens())
        {
            if(!setContainsName(bookSetTmp, specimen.getTitle()))
            {
                bookSetTmp.add(new Book(bookSetTmp.size(), specimen.getTitle(), specimen.getAuthor()));
            }
        }

        bookSet = bookSetTmp;
    }

    @Override
    public void addBookToList(Book book) {
        bookSet.add(book);
    }
}
