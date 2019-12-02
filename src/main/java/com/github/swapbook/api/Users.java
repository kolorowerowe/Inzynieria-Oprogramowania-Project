package com.github.swapbook.api;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.specimen.FakeSpecimenRepository;
import com.github.swapbook.repositories.specimen.SpecimenRepository;
import com.github.swapbook.repositories.users.FakeUserRepository;
import com.github.swapbook.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Users {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SpecimenRepository specimenRepository;

    public Users() {
        userRepository = new FakeUserRepository();
        specimenRepository = new FakeSpecimenRepository();
    }

    @GetMapping("/api/users/all")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.getUsers());
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        return ResponseEntity.ok().body(userRepository.getUserById(userId));
    }

    @PostMapping("/api/users/put")
    public void createUser(@RequestBody User user) {
        userRepository.addToList(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(@PathVariable(value = "id") int userId) {
        userRepository.deleteUserById((userId));
    }


    @GetMapping("api/users/specimens/{id}")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getUsersSpecimens(@PathVariable(value = "id") int userId) {
        return ResponseEntity.ok().body(userRepository.getUserById(userId).getSpecimenList());
    }

    @PostMapping("/api/users/specimens/{id}")
    public void addSpecimenToUser(@PathVariable(value = "id") int userId, @RequestBody Specimen specimen) {
        specimen.setUserId(userId);
        specimenRepository.addToList(specimen);
        userRepository.addSpecimen(userId, specimen);
    }

    @DeleteMapping("/api/users/specimens/{id}/{specimenId}")
    public void deleteSpecimenFromUser(@PathVariable(value = "id") int userId, @PathVariable(value = "specimenId") int specimenId) {
        userRepository.deleteSpecimen(userId, specimenId);
    }

    @GetMapping("/api/specimens/all")
    @ResponseBody
    public ResponseEntity<List<Specimen>> getSpecimens() {
        return ResponseEntity.ok().body(specimenRepository.getSpecimens());
    }
}
