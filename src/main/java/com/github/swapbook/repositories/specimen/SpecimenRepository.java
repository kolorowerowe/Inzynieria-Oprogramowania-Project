package com.github.swapbook.repositories.specimen;

import com.github.swapbook.model.Specimen;
import java.util.List;

public interface SpecimenRepository {
    List<Specimen> getSpecimens();
    Specimen getSpecimenById(int id);
    void addToList(Specimen specimen);
    void deleteSpecimenById(int id);
}
