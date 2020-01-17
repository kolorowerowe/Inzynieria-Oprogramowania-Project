package com.github.swapbook.repositories.opinions;

import com.github.swapbook.model.Opinion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OpinionService implements OpinionServiceI {

    @Autowired
    OpinionRepository opinionRepository;

    @Override
    public List<Opinion> getOpinionsForUser(int receiver_id) {
        return opinionRepository.findAll().stream().filter(o -> o.getReceiver_id() == receiver_id).collect(Collectors.toList());
    }

    @Override
    public List<Opinion> getOpinionsFromUser(int giver_id) {
        return opinionRepository.findAll().stream().filter(o -> o.getGiver_id() == giver_id).collect(Collectors.toList());
    }

    @Override
    public List<Opinion> getAllOpinions() {
        return opinionRepository.findAll();
    }

    @Override
    public Opinion getOpinionById(int opinion_id) {
        return opinionRepository.findById(opinion_id).orElse(null);
    }

    @Override
    public void addOpinion(Opinion opinion) {
        opinionRepository.save(opinion);
    }

    @Override
    public void deleteOpinionById(int opinion_id) {
        opinionRepository.deleteById(opinion_id);
    }
}
