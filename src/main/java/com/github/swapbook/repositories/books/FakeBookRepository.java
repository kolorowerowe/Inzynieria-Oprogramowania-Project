package com.github.swapbook.repositories.books;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Review;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.specimens.SpecimenDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
    public BookRepository searchBooksByRegex(String regex){
        BookRepository resultRepository = new FakeBookRepository();
        Pattern compiledPattern = Pattern.compile(regex);

        for (Book book:this.bookSet) {
            Matcher matcher =compiledPattern.matcher(book.getTitle());
            if(matcher.find())
                resultRepository.addBookToList(book);
        }

        return resultRepository;
    }

    @Override
    public BookRepository searchBooksByAuthor(String regex){
        BookRepository resultRepository = new FakeBookRepository();
        Pattern compiledPattern = Pattern.compile(regex);

        for (Book book:this.bookSet) {
            Matcher matcher =compiledPattern.matcher(book.getAuthor());
            if(matcher.find())
                resultRepository.addBookToList(book);
        }

        return resultRepository;
    }

    @Override
    public void addBookToList(Book book) {
        if(book != null) {
            bookSet.add(book);
        }
    }
}
