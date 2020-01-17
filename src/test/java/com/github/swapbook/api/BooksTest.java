package com.github.swapbook.api;

import com.github.swapbook.model.Book;
import com.github.swapbook.repositories.books.BookDBRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringJUnit4ClassRunner.class)
public class BooksTest {
    private MockMvc mockMvc;

    @Mock
    private BookDBRepository bookRepository;

    @InjectMocks
    private Books books;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(books).build();
    }

    @Test
    public void getAllBooks_shouldReturnEmptyList() throws Exception {

        List<Book> listOfBooks = new LinkedList<>();

        when(bookRepository.getBooks()).thenReturn(listOfBooks);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(0)));
    }

    @Test
    public void getAllBooks_shouldReturnListWithTwoBooks() throws Exception {

        Book book1 = new Book(12, "Tytuł, lol", "author 11", "url://url");
        Book book2 = new Book(13, "Tytuł, lol", "author 222222");
        List<Book> listOfBooks = Arrays.asList(book1, book2);

        when(bookRepository.getBooks()).thenReturn(listOfBooks);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(2)))
                .andExpect(jsonPath("$.[0].*", Matchers.hasSize(4)))
                .andExpect(jsonPath("$.[1].*", Matchers.hasSize(4)))
                .andExpect(jsonPath("$.[0].book_id", Matchers.is(book1.getBook_id())))
                .andExpect(jsonPath("$.[0].title", Matchers.is(book1.getTitle())))
                .andExpect(jsonPath("$.[0].author", Matchers.is(book1.getAuthor())))
                .andExpect(jsonPath("$.[0].photo_url", Matchers.is(book1.getPhoto_url())))
                .andExpect(jsonPath("$.[1].book_id", Matchers.is(book2.getBook_id())))
                .andExpect(jsonPath("$.[1].title", Matchers.is(book2.getTitle())))
                .andExpect(jsonPath("$.[1].author", Matchers.is(book2.getAuthor())))
                .andExpect(jsonPath("$.[1].photo_url", Matchers.is(""))
                );
    }

    @Test
    public void getBookById_shouldReturnCorrectBook() throws Exception{
        final int id = 15;

        Book book1 = new Book(id, "Tytuł, lol", "author 11", "url://url");

        when(bookRepository.getBookById(id)).thenReturn(book1);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/books/"+id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(4)))
                .andExpect(jsonPath("$.book_id", Matchers.is(book1.getBook_id())))
                .andExpect(jsonPath("$.title", Matchers.is(book1.getTitle())))
                .andExpect(jsonPath("$.author", Matchers.is(book1.getAuthor())))
                .andExpect(jsonPath("$.photo_url", Matchers.is(book1.getPhoto_url())));

    }

}