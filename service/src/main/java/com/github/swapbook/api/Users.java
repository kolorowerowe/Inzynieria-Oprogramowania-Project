package com.github.swapbook.api;

import com.github.swapbook.model.User;
import com.github.swapbook.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Users {

    @Autowired
    UsersRepository users;

    @GetMapping("/api/users/all")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(users.getUsers());
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") int userId) {
        return ResponseEntity.ok().body(users.getUserById(userId));
    }

    @PostMapping("/api/users/put")
    public void createUser(@RequestBody User user) {
        users.addToList(user);
    }


}
