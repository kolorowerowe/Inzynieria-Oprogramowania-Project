package com.github.swapbook.api;

import com.github.swapbook.model.Book;
import com.github.swapbook.repositories.books.BookDBRepository;
import com.github.swapbook.repositories.specimens.SpecimenDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Books {

    @Autowired
    SpecimenDBRepository specimenRepository;

    @Autowired
    BookDBRepository bookRepository;

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
