package com.github.swapbook.api;

import com.github.swapbook.model.Opinion;
import com.github.swapbook.repositories.opinions.OpinionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Opinions {

    @Autowired
    OpinionService opinionService;

    @GetMapping("/api/opinions/all")
    @ResponseBody
    public ResponseEntity<List<Opinion>> getAllOpinions() {
        return ResponseEntity.ok().body(opinionService.getAllOpinions());
    }

    @GetMapping("/api/opinions/giver/{id}")
    @ResponseBody
    public ResponseEntity<List<Opinion>> getOpinionsFromUser(@PathVariable(value = "id") int giver_id) {
        return ResponseEntity.ok().body(opinionService.getOpinionsFromUser(giver_id));
    }

    @GetMapping("/api/opinions/receiver/{id}")
    @ResponseBody
    public ResponseEntity<List<Opinion>> getOpinionsForUser(@PathVariable(value = "id") int receiver_id) {
        return ResponseEntity.ok().body(opinionService.getOpinionsForUser(receiver_id));
    }

    @GetMapping("/api/opinions/{id}")
    @ResponseBody
    public ResponseEntity<Opinion> getOpinionById(@PathVariable(value = "id") int reviev_id) {
        return ResponseEntity.ok().body(opinionService.getOpinionById(reviev_id));
    }


    @PostMapping("/api/opinions/put")
    public void createOpinion(@RequestBody Opinion opinion) {
        opinionService.addOpinion(opinion);
    }

    @DeleteMapping("/api/opinions/{id}")
    public void deleteOpinion(@PathVariable(value = "id") int opinion_id) {
        opinionService.deleteOpinionById(opinion_id);
    }
}
