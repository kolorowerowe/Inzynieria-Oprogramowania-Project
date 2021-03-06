package com.github.swapbook.api;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.SearchQuery;
//import com.github.swapbook.repositories.books.BookDBRepository;
import com.github.swapbook.repositories.books.BookService;
import com.github.swapbook.repositories.specimens.SpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Books {

    @Autowired
    SpecimenService specimenService;

    @Autowired
    BookService bookService;

    private List<Book> searchBookRepository;

    public Books() {
        List<Book> searchBookRepository = new LinkedList<>();
    }

    @PostConstruct
    public void loadSearchBookRepository(){
        searchBookRepository = bookService.searchBooksByAuthor(".*");
    }

    @GetMapping("/api/books/all")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok().body(bookService.getAllBooks());
    }

    @GetMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") int bookId) {
        return ResponseEntity.ok().body(bookService.getBookById(bookId));
    }

    @GetMapping("/api/books/search/result")
    @ResponseBody
    public ResponseEntity<List<Book>> getSearchResult() {
        return ResponseEntity.ok().body(searchBookRepository);
    }

    @PostMapping("/api/books/search/title/regex")
    public void searchBooksByRegex(@RequestBody SearchQuery query) {
        if (query.getAttribute().equals("book")) {
            searchBookRepository = bookService.searchBooksByRegex(query.getValue());
        } else if (query.getAttribute().equals("author")) {
            searchBookRepository = bookService.searchBooksByAuthor(query.getValue());
        }
    }

    @DeleteMapping("/api/books/delete/{id}")
    public void deleteBook(@PathVariable(value = "id") int book_id) {
        bookService.deleteBookById(book_id);
    }

    @PostMapping("/api/books/put")
    public void createBook(@RequestBody Book book) {
        bookService.addBook(book);
    }
}
