package com.github.swapbook.api;

import com.github.swapbook.model.Opinion;
import com.github.swapbook.repositories.opinions.OpinionDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Opinions {

    @Autowired
    OpinionDBRepository opinionRepository;

    @GetMapping("/api/opinions/all")
    @ResponseBody
    public ResponseEntity<List<Opinion>> getAllOpinions() {
        return ResponseEntity.ok().body(opinionRepository.getAllOpinions());
    }

    @GetMapping("/api/opinions/giver/{id}")
    @ResponseBody
    public ResponseEntity<List<Opinion>> getOpinionsFromUser(@PathVariable(value = "id") int giver_id) {
        return ResponseEntity.ok().body(opinionRepository.getOpinionsFromUser(giver_id));
    }

    @GetMapping("/api/opinions/receiver/{id}")
    @ResponseBody
    public ResponseEntity<List<Opinion>> getOpinionsForUser(@PathVariable(value = "id") int receiver_id) {
        return ResponseEntity.ok().body(opinionRepository.getOpinionsForUser(receiver_id));
    }

    @GetMapping("/api/opinions/{id}")
    @ResponseBody
    public ResponseEntity<Opinion> getOpinionById(@PathVariable(value = "id") int reviev_id) {
        return ResponseEntity.ok().body(opinionRepository.getOpinionById(reviev_id));
    }


    @PostMapping("/api/opinions/put")
    public void createOpinion(@RequestBody Opinion opinion) {
        opinionRepository.addOpinion(opinion);
    }

    @DeleteMapping("/api/opinions/{id}")
    public void deleteOpinion(@PathVariable(value = "id") int opinion_id) {
        opinionRepository.deleteOpinionById(opinion_id);
    }
}
