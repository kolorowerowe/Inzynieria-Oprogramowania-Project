package com.github.swapbook.repositories.specimen;

import com.github.swapbook.model.Specimen;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FakeSpecimenRepositoryTest {

    @Test
    public void getSpecimens_ShouldReturnEmptyList() {
        // Arrange
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();

        // Act
        List<Specimen> specimenList = specimenRepository.getSpecimens();

        // Assert
        assertEquals(true, specimenList.isEmpty());
    }

    @Test
    public void getSpecimens_ShouldReturnAllSpecimens() {
        // Arrange
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();
        specimenRepository.addToList(new Specimen(1,1, "ksiazka1"));
        specimenRepository.addToList(new Specimen(2,1, "ksiazka2"));
        specimenRepository.addToList(new Specimen(3,1, "ksiazka3"));

        // Act
        List<Specimen> specimenList = specimenRepository.getSpecimens();

        // Assert

        assertEquals(false, specimenList.isEmpty());
        assertEquals(3, specimenList.size());
    }

    @Test
    void getSpecimenById_ShouldReturnNullForNotExistingId() {
        // Arrange
        int notExistingId = 4;
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();
        specimenRepository.addToList(new Specimen(1,1, "ksiazka1"));
        specimenRepository.addToList(new Specimen(2,1, "ksiazka2"));
        specimenRepository.addToList(new Specimen(3,1, "ksiazka3"));

        // Act
        Specimen specimen = specimenRepository.getSpecimenById(notExistingId);

        // Assert

        assertNull(specimen);
    }

    @Test
    void getSpecimenById_ShouldReturnSpecimenForExistingId() {
        // Arrange
        int id = 3;
        String name = "ksiazka3";
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();
        specimenRepository.addToList(new Specimen(1,1, "ksiazka1"));
        specimenRepository.addToList(new Specimen(2,1, "ksiazka2"));
        specimenRepository.addToList(new Specimen(id, id, name));

        // Act
        Specimen specimen = specimenRepository.getSpecimenById(id);

        // Assert
        assertNotNull(specimen);
        assertEquals(id, specimen.getId());
        assertEquals(id, specimen.getUserId());
        assertEquals(name, specimen.getName());
    }

    @Test
    void addToList_ShouldNotAddNull() {
        // Arrange
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();

        // Act
        specimenRepository.addToList(null);

        // Assert

        assertEquals(true, specimenRepository.getSpecimens().isEmpty());
    }

    @Test
    void addToList_ShouldAddSpecimen() {
        // Arrange
        int id = 3;
        String name = "ksiazka3";
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();
        Specimen specimen = new Specimen(id, id, name);

        // Act
        specimenRepository.addToList(specimen);

        // Assert

        assertEquals(false, specimenRepository.getSpecimens().isEmpty());
        assertEquals(1, specimenRepository.getSpecimens().size());
    }

    @Test
    void deleteSpecimenById_ShouldNotDeleteNotExistingId() {
        // Arrange
        int notExistingId = 4;
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();
        specimenRepository.addToList(new Specimen(1,1, "ksiazka1"));
        specimenRepository.addToList(new Specimen(2,1, "ksiazka2"));
        specimenRepository.addToList(new Specimen(3,1, "ksiazka3"));

        // Act
        specimenRepository.deleteSpecimenById(notExistingId);

        // Assert
        assertEquals(false, specimenRepository.getSpecimens().isEmpty());
        assertEquals(3, specimenRepository.getSpecimens().size());
    }

    @Test
    void deleteSpecimenById_ShouldDeleteExistingId() {
        // Arrange
        int id = 1;
        SpecimenRepository specimenRepository = new FakeSpecimenRepository();
        specimenRepository.addToList(new Specimen(1,1, "ksiazka1"));

        // Act
        specimenRepository.deleteSpecimenById(id);

        // Assert
        assertEquals(true, specimenRepository.getSpecimens().isEmpty());
    }
}