package com.github.swapbook.repositories.book;

import com.github.swapbook.repositories.specimen.FakeSpecimenRepository;
import com.github.swapbook.repositories.specimen.SpecimenRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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