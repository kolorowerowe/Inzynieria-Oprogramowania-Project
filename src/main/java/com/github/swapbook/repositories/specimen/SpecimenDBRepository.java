package com.github.swapbook.repositories.specimen;

import com.github.swapbook.model.Specimen;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("swapbook.specimens")
public class SpecimenDBRepository implements SpecimenRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public SpecimenDBRepository() {
    }

    @Override
    public List<Specimen> getSpecimens() {
        return entityManager.createNativeQuery("select * from swapbook.specimens", Specimen.class).getResultList();
    }

    @Override
    public Specimen getSpecimenById(int id) {
        return ((Specimen) entityManager.createNativeQuery("select * from swapbook.specimens WHERE specimen_id=?", Specimen.class)
                .setParameter(1, id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void addToList(Specimen specimen) {
        entityManager.createNativeQuery("INSERT INTO swapbook.specimens VALUES (?,?,?,?,?,?,?,?,?,?,?,?)")
                .setParameter(1, specimen.getSpecimen_id())
                .setParameter(2, specimen.getBook_id())
                .setParameter(3, specimen.getUser_id())
                .setParameter(4, specimen.getTitle())
                .setParameter(5, specimen.getCondition())
                .setParameter(6, specimen.getNumber_pages())
                .setParameter(7, specimen.getAuthor())
                .setParameter(8, specimen.getRelease_date())
                .setParameter(9, specimen.getIssue_number())
                .setParameter(10, specimen.getIsbn())
                .setParameter(11, specimen.getLoan_period())
                .setParameter(12, specimen.getPhoto_url())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteSpecimenById(int id) {
        entityManager.createNativeQuery("delete from swapbook.specimens WHERE  specimen_id=?", Specimen.class)
                .setParameter(1, id)
                .executeUpdate();
    }
}
