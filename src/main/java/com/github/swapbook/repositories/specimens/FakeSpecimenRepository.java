package com.github.swapbook.repositories.specimens;

import com.github.swapbook.model.Specimen;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

@Component
public class FakeSpecimenRepository implements SpecimenRepository {
    private List<Specimen> listSpecimens = new LinkedList<>();

    public FakeSpecimenRepository() {
        //listSpecimens.add(new Specimen("W pustynii i w puszczy","Good",500,"Henryk Sienkiewicz"));
    }


    @Override
    public List<Specimen> getSpecimens() {
        return listSpecimens;
    }

    @Override
    public Specimen getSpecimenById(int id) {
        return listSpecimens.stream()
                .filter(s -> s.getSpecimen_id() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addToList(Specimen specimen) {
        if(specimen != null)
        {
            specimen.setSpecimen_id(this.listSpecimens.size() + 1);
            listSpecimens.add(specimen);
        }
    }

    @Override
    public void deleteSpecimenById(int id) {
        listSpecimens.remove(getSpecimenById(id));
    }
}
