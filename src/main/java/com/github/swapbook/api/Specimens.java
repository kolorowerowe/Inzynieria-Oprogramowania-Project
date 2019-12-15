package com.github.swapbook.api;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.specimens.SpecimenDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Specimens {

    @Autowired
    SpecimenDBRepository specimenRepository;

    @GetMapping("/api/specimens/all")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getSpecimens() {
        return ResponseEntity.ok().body(specimenRepository.getSpecimens());
    }

    @GetMapping("/api/specimens/{id}")
    @ResponseBody
    public ResponseEntity<Specimen> getSpecimenById(@PathVariable(value = "id") int specimen_id) {
        return ResponseEntity.ok().body(specimenRepository.getSpecimenById(specimen_id));
    }

    @PostMapping("/api/specimens/put")
    public void createSpecimen(@RequestBody Specimen specimen) {
        specimenRepository.addToList(specimen);
    }

    @DeleteMapping("/api/specimens/{id}")
    public void deleteSpecimen(@PathVariable(value = "id") int specimen_id) {
        specimenRepository.deleteSpecimenById(specimen_id);
    }

}
