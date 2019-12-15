package com.github.swapbook.repositories.opinions;

import com.github.swapbook.model.Opinion;

import java.util.List;

public interface OpinionRepository {
    List<Opinion> getOpinionsForUser(int receiver_id);
    List<Opinion> getOpinionsFromUser(int giver_id);
    List<Opinion> getAllOpinions();
    Opinion getOpinionById(int opinion_id);
    void addOpinion(Opinion opinion);
    void deleteOpinionById(int opinion_id);

}
