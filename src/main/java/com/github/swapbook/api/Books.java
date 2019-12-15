package com.github.swapbook.api;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.SearchQuery;
import com.github.swapbook.repositories.books.BookDBRepository;
import com.github.swapbook.repositories.books.BookRepository;
import com.github.swapbook.repositories.specimens.SpecimenDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class Books {

    @Autowired
    SpecimenDBRepository specimenRepository;

    @Autowired
    BookDBRepository bookRepository;

    private BookRepository searchBookRepository;

    public Books() {
        searchBookRepository = new BookDBRepository();
    }

    @PostConstruct
    public void loadSearchBookRepository(){
        searchBookRepository = bookRepository.searchBooksByAuthor(".*");
    }

    @GetMapping("/api/books/all")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok().body(bookRepository.getBooks());
    }

    @GetMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") int bookId) {
        return ResponseEntity.ok().body(bookRepository.getBookById(bookId));
    }

    @GetMapping("/api/books/search/result")
    @ResponseBody
    public ResponseEntity<List<Book>> getSearchResult() {
        return ResponseEntity.ok().body(searchBookRepository.getBooks());
    }

    @PostMapping("/api/books/search/title/regex")
    public void searchBooksByRegex(@RequestBody SearchQuery query) {
        if (query.getAttribute().equals("book")) {
            searchBookRepository = bookRepository.searchBooksByRegex(query.getValue());
        } else if (query.getAttribute().equals("author")) {
            searchBookRepository = bookRepository.searchBooksByAuthor(query.getValue());
        }

    }

    @GetMapping("/api/books/name/{title}")
    @ResponseBody
    public ResponseEntity<Book> getBookByTitle(@PathVariable(value = "title") String title) {
        return ResponseEntity.ok().body(bookRepository.getBookByTitle(title));
    }

    @PostMapping("/api/books/put")
    public void createSpecimen(@RequestBody Book book) {
        bookRepository.addBookToList(book);
    }

    @DeleteMapping("/api/books/{id}")
    public void deleteSpecimen(@PathVariable(value = "id") int book_id) {
        bookRepository.deleteBookById(book_id);
    }

}
