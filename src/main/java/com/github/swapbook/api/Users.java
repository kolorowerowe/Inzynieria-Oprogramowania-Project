package com.github.swapbook.api;

import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Users {

    @Autowired
    UserDBRepository userRepository;

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

}
