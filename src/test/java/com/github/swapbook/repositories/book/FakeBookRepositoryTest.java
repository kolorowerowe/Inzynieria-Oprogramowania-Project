package com.github.swapbook.repositories.book;

import com.github.swapbook.model.Book;
import com.github.swapbook.repositories.specimen.FakeSpecimenRepository;
import com.github.swapbook.repositories.specimen.SpecimenRepository;
import com.github.swapbook.repositories.users.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FakeBookRepositoryTest {

    private SpecimenRepository specimenRepository;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        specimenRepository = new FakeSpecimenRepository();
        bookRepository = new FakeBookRepository();
    }

    @Test
    void getBooks_ShouldReturnEmptySet() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void getBookById() {

        //Arrange
        int id=1;
        String name = "book1";
        String author = "author1";
        Book book = new Book(id,name,author);

        //Act
        bookRepository.addBook(book);
        Book book1 = bookRepository.getBookById(id);

        //Assert
        assertTrue(bookRepository.getBooks().contains(book1));
        assertEquals(book,book1);
    }

    @Test
    void getBookByName() {
        //Arrange
        int id=1;
        String name = "book1";
        String author = "author1";
        Book book = new Book(id,name,author);

        //Act
        bookRepository.addBook(book);
        Book book1 = bookRepository.getBookByName(name);

        //Assert
        assertTrue(bookRepository.getBooks().contains(book1));
        assertEquals(book,book1);
    }

    @Test
    void setContainsName() {
        //Arrange
        Set<Book> setBook = new HashSet<>();
        int id=1;
        String title = "book1";
        String author = "author1";
        Book book = new Book(id,title,author);

        //Act
        setBook.add(book);
        boolean result = bookRepository.setContainsName(setBook,title);

        //Assert
        assertTrue(result);
    }

    @Test
    void addToList() {
        //Arrange
        int id=1;
        String name = "book1";
        String author = "author1";
        Book book = new Book(id,name,author);
        //Act
        bookRepository.addBook(book);

        //Assert
        assertTrue(bookRepository.getBooks().contains(book));
    }

    @Test
    void deleteSpecimenById() {
    }

    @Test
    void updateUniqueBooks() {
    }

    @Test
    void getBooks() {
        //Arrange
        int id=1;
        String name = "book";
        String author = "author";
        Book book = new Book(id,name,author);

        int id1 = 2;
        String name1 = "book1";
        String author1 = "author1";
        Book book1 = new Book(id1,name1,author1);

        int id2 = 3;
        String name2 = "book2";
        String author2 = "author2";
        Book book2 = new Book(id2,name2,author2);

        //Act
        bookRepository.addBook(book);
        bookRepository.addBook(book1);


        //Assert
        assertTrue(bookRepository.getBooks().contains(book));
        assertTrue(bookRepository.getBooks().contains(book1));
        assertFalse(bookRepository.getBooks().contains(book2));


    }

    @Test
    void testGetBookById() {
    }

    @Test
    void testGetBookByName() {
    }

    @Test
    void testSetContainsName() {
    }

    @Test
    void searchBooksByRegex() {
    }

    @Test
    void searchBooksByAuthor() {
    }

    @Test
    void testUpdateUniqueBooks() {
    }

    @Test
    void addReviewToBook() {
    }

    @Test
    void addBook() {
    }
}