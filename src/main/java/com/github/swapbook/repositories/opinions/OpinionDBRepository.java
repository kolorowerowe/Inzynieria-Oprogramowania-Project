package com.github.swapbook.repositories.opinions;

import com.github.swapbook.model.Opinion;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("swapbook.opinions")
public class OpinionDBRepository implements OpinionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public OpinionDBRepository() {
    }

    @Override
    public List<Opinion> getOpinionsForUser(int receiver_id) {
        return entityManager.createNativeQuery("select * from swapbook.opinions where receiver_id = ?", Opinion.class)
                .setParameter(1, receiver_id)
                .getResultList();
    }

    @Override
    public List<Opinion> getOpinionsFromUser(int giver_id) {
        return entityManager.createNativeQuery("select * from swapbook.opinions where giver_id = ?", Opinion.class)
                .setParameter(1, giver_id)
                .getResultList();
    }

    @Override
    public List<Opinion> getAllOpinions() {
        return entityManager.createNativeQuery("select * from swapbook.opinions", Opinion.class).getResultList();
    }

    @Override
    public Opinion getOpinionById(int opinion_id) {
        return ((Opinion) entityManager.createNativeQuery("select * from swapbook.opinions WHERE opinion_id=?", Opinion.class)
                .setParameter(1, opinion_id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void addOpinion(Opinion opinion) {
        entityManager.createNativeQuery("INSERT INTO swapbook.opinions VALUES (?,?,?,?,?,?)")
                .setParameter(1, opinion.getOpinion_id())
                .setParameter(2, opinion.getGiver_id())
                .setParameter(3, opinion.getReceiver_id())
                .setParameter(4, opinion.getText())
                .setParameter(5, opinion.getMark())
                .setParameter(6, opinion.getDate())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteOpinionById(int opinion_id) {
        entityManager.createNativeQuery("delete from swapbook.opinions WHERE  opinion_id=?", Opinion.class)
                .setParameter(1, opinion_id)
                .executeUpdate();
    }
}

