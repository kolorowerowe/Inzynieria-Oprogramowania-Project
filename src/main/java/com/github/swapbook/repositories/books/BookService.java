package com.github.swapbook.repositories.books;

import com.github.swapbook.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookService() {
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Book getBookById(int id) {
        return bookRepository.findById((long) id).orElse(null);
    }
    public void deleteBookById(int book_id){
        bookRepository.deleteById((long)book_id);
    }

    public void addBook(Book b){
        bookRepository.save(b);
    }

    public List<Book> getBooksByTitle(String title) {
        return getAllBooks().stream().filter(x->x.getTitle().equals(title)).collect(Collectors.toList());
    }


    public List<Book>  searchBooksByRegex(String regex) {
        String my_regex = ".*" + regex + ".*";
        return getAllBooks().stream().filter(x -> x.getTitle().toLowerCase().matches(my_regex.toLowerCase())).collect(Collectors.toList());
    }

    public List<Book> searchBooksByAuthor(String regex) {
        String my_regex = ".*" + regex + ".*";
        return getAllBooks().stream().filter(x -> x.getAuthor().toLowerCase().matches(my_regex.toLowerCase())).collect(Collectors.toList());

    }
}