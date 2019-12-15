package com.github.swapbook.repositories.books;

import com.github.swapbook.repositories.specimens.FakeSpecimenRepository;
import com.github.swapbook.repositories.specimens.SpecimenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FakeBookRepositoryTest {

    private SpecimenRepository specimenRepository;

    @BeforeEach
    void setUp() {
        specimenRepository = new FakeSpecimenRepository();
    }

    @Test
    void getBooks_ShouldReturnEmptySet() {
        //Arrange

        //Act

        //Assert
    }

    @Test
    void getBookById() {
    }

    @Test
    void getBookByName() {
    }

    @Test
    void setContainsName() {
    }

    @Test
    void addToList() {
    }

    @Test
    void deleteSpecimenById() {
    }

    @Test
    void updateUniqueBooks() {
    }
}