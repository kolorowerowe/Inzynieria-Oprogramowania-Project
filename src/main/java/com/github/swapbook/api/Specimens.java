package com.github.swapbook.api;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.repositories.book.BookRepository;
import com.github.swapbook.repositories.book.FakeBookRepository;
import com.github.swapbook.repositories.specimen.FakeSpecimenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Specimens {

    @Autowired
    FakeSpecimenRepository specimenRepository;

    @Autowired
    BookRepository bookRepository;

    public Specimens()
    {
        specimenRepository = new FakeSpecimenRepository();
        bookRepository = new FakeBookRepository();
    }

    @GetMapping("/api/specimens/all")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getAllSpecimens()
    {
        return ResponseEntity.ok().body(specimenRepository.getSpecimens());
    }

    @PostMapping("/api/specimens/put")
    public void createSpecimen(@RequestBody Specimen specimen){ specimenRepository.addToList(specimen);}


}
