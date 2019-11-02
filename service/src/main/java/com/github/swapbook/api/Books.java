package com.github.swapbook.api;

import com.github.swapbook.model.Book;
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

    public Books() {
        bookRepository = new FakeBookRepository();
        specimenRepository = new FakeSpecimenRepository();
    }

    @GetMapping("/api/books/all")
    @ResponseBody
    public ResponseEntity<List<Book>> getAllBooks() {
        bookRepository.updateUniqueBooks();
        return ResponseEntity.ok().body(bookRepository.getBooks());
    }

    @GetMapping("/api/books/{id}")
    @ResponseBody
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") int bookId) {
        return ResponseEntity.ok().body(bookRepository.getBookById(bookId));
    }
}
