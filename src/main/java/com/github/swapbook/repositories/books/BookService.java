package com.github.swapbook.repositories.books;

import com.github.swapbook.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return bookRepository.getOne((long) id);
    }
    public void deleteBookById(int book_id){
        bookRepository.deleteById((long)book_id);
    }

    public void addBook(Book b){
        bookRepository.save(b);
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