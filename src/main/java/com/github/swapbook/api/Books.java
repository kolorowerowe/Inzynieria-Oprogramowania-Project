package com.github.swapbook.api;

import com.github.swapbook.model.Book;
import com.github.swapbook.model.Review;
import com.github.swapbook.model.SearchQuery;
import com.github.swapbook.repositories.book.BookRepository;
import com.github.swapbook.repositories.book.FakeBookRepository;
import com.github.swapbook.repositories.specimen.FakeSpecimenRepository;
import com.github.swapbook.repositories.specimen.SpecimenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Books {

    @Autowired
    SpecimenRepository specimenRepository;

    @Autowired
    BookRepository bookRepository;

    BookRepository searchBookRepository;

    public Books() {
        bookRepository = new FakeBookRepository();
        specimenRepository = new FakeSpecimenRepository();
        searchBookRepository = new FakeBookRepository();
    }

    @GetMapping("/api/books/all")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks() {
        //bookRepository.updateUniqueBooks();
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
        }
        else if(query.getAttribute().equals("author")){
            searchBookRepository = bookRepository.searchBooksByAuthor(query.getValue());
        }

    }

    @PostMapping("/api/books/review/{id}")
    public void createBookReview(@PathVariable(value = "id") int bookId, @RequestBody Review review) {
        bookRepository.addReviewToBook(bookId, review);
    }

    @PostMapping("/api/books/put")
    public void createBook(@RequestBody Book book){ bookRepository.addBook(book);}

}
