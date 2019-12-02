package com.github.swapbook.repositories.specimen;

import com.github.swapbook.model.Specimen;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class FakeSpecimenRepository implements SpecimenRepository {
    private List<Specimen> listSpecimens = new LinkedList<>();

    @Override
    public List<Specimen> getSpecimens() {
        return listSpecimens;
    }

    @Override
    public Specimen getSpecimenById(int id) {
        return listSpecimens.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public Specimen addToList(Specimen specimen) {
        if(specimen != null)
        {
            specimen.setId(this.listSpecimens.size() + 1);
            listSpecimens.add(specimen);
            return specimen;
        }
        return (Specimen)null;
    }

    @Override
    public void deleteSpecimenById(int id) {
        listSpecimens.remove(getSpecimenById(id));
    }
}
