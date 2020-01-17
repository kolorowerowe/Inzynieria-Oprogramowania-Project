package com.github.swapbook.api;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.specimens.SpecimenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.List;

@RestController
public class Specimens {

    private List<Specimen> specimenList;
    @Autowired
    SpecimenService specimenService;

    private Specimens(){specimenList=new LinkedList<>();}

    @PostConstruct
    public void loadSpecimenList()
    {
        specimenList = specimenService.getSpecimens();
    }

    @GetMapping("/api/specimens/all")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getSpecimens() {
        return ResponseEntity.ok().body(specimenService.getSpecimens());
    }

    @GetMapping("/api/specimens/{id}")
    @ResponseBody
    public ResponseEntity<Specimen> getSpecimenById(@PathVariable(value = "id") int specimen_id) {
        return ResponseEntity.ok().body(specimenService.getSpecimenById(specimen_id));
    }

    @PostMapping("/api/specimens/bookId/{id}")
    public void getSpecimensWithBookIdEqual(@PathVariable(value="id") int bookId){
        specimenList = specimenService.getSpecimensWithBookIdEqual(bookId);
    }

    @GetMapping("/api/specimens/bookId/result")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getResultSpecimen() {
        return ResponseEntity.ok().body(specimenList);
    }

    @PostMapping("/api/specimens/put")
    public void createSpecimen(@RequestBody Specimen specimen) {
            specimenService.addToList(specimen);
    }

    @DeleteMapping("/api/specimens/{id}")
    public void deleteSpecimen(@PathVariable(value = "id") int specimen_id) {
        specimenService.deleteSpecimenById(specimen_id);
    }

}
