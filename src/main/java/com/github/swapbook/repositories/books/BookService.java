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


    public BookService searchBooksByRegex(String regex) {
        BookService resultRepository = new BookService();
        Pattern compiledPattern = Pattern.compile(regex);

        List<Book> bookList = getAllBooks();

        for (Book book:bookList) {
            Matcher matcher =compiledPattern.matcher(book.getTitle());
            if(matcher.find())
                resultRepository.addBook(book);
        }

        return resultRepository;
    }

    public BookService searchBooksByAuthor(String regex) {
        BookService resultRepository = new BookService();
        Pattern compiledPattern = Pattern.compile(regex);

        List<Book> bookList = getAllBooks();

        for (Book book:bookList) {
            Matcher matcher =compiledPattern.matcher(book.getAuthor());
            if(matcher.find())
                resultRepository.addBook(book);
        }

        return resultRepository;
    }
}