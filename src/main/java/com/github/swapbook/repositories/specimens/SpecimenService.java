package com.github.swapbook.repositories.specimens;


import com.github.swapbook.model.Book;
import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.books.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecimenService {

    @Autowired
    SpecimenRepository specimenRepository;

    @Autowired
    BookService bookService;

    public List<Specimen> getSpecimens() {
        return specimenRepository.findAll();
    }

    public Specimen getSpecimenById(int id) {
        return specimenRepository.findById(id).orElse(null);
    }

    public void addToList(Specimen specimen) {
        specimenRepository.save(specimen);

        List<Book> listOfBooks = bookService.getBooksByTitle(specimen.getTitle());
        if (listOfBooks.isEmpty()){
            Book newBook = new Book();
            newBook.setTitle(specimen.getTitle());
            newBook.setAuthor(specimen.getAuthor());
            newBook.setPhoto_url(specimen.getPhoto_url());
            bookService.addBook(newBook);
        }

    }

    public void deleteSpecimenById(int id) {
        specimenRepository.deleteById(id);
    }
}
