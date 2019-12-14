package com.github.swapbook.repositories.book;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Review;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.specimen.SpecimenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Component
public class FakeBookRepository implements BookRepository {
    private Set<Book> bookSet = new HashSet<>();

    @Autowired
    SpecimenRepository specimenRepository;

    @Override
    public List<Book> getBooks() {
        return new LinkedList<>(bookSet);
    }

    @Override
    public Book getBookById(int id) {
        return bookSet.stream()
                .filter(b -> b.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Book getBookByName(String name) {
        return bookSet.stream()
                .filter(b -> b.getName().equals(name))
                .findAny()
                .orElse(null);
    }

    public boolean setContainsName(Set<Book> set, String name) {
        return set.stream().anyMatch(b -> b.getName().equals(name));
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

        //bookSet = bookSetTmp;
        bookSet = new HashSet<>(bookSetTmp);

    }

    @Override
    public void addReviewToBook(int bookId, Review review) {
        Book book = bookSet.stream().filter(b->b.getId() == bookId).findAny().orElse(null);
        this.bookSet.remove(book);
        if(book != null)
            book.addReview(review);
        this.bookSet.add(book);
    }

    @Override
    public void addBook(Book book) {
        if(book != null) {
            book.setId(this.bookSet.size() + 1);
            bookSet.add(book);
        }
    }
}
