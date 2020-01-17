package com.github.swapbook.repositories.specimens;

import com.github.swapbook.model.Specimen;
import java.util.List;

public interface SpecimenRepository {
    List<Specimen> getSpecimens();
    Specimen getSpecimenById(int id);
    List<Specimen> getSpecimensWithBookIdEqual(int bookId) throws Exception;
    void addToList(Specimen specimen);
    void deleteSpecimenById(int id);
}
