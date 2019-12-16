package com.github.swapbook.api;

import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserDBRepository;
import com.github.swapbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Users {
    //@Autowired
    UserDBRepository userRepository;

    UserService userService;

    @Autowired
    public Users (UserDBRepository userRepository,
                  UserService userService)
    {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping("/api/users/all")
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok().body(userRepository.getUsers());
    }

    @GetMapping("/api/users/{id}")
    @ResponseBody
    public ResponseEntity<User> getUserById(HttpServletRequest request, @PathVariable(value = "id") int userId) {
        if(userService.VerifyToken(request, userId))
            return ResponseEntity.ok().body(userRepository.getUserById(userId));
        else
            return  ResponseEntity.badRequest().body(null);
    }

    @PostMapping("/api/users/put")
    public void createUser(@RequestBody User user) {
        userRepository.addToList(user);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(HttpServletRequest request, @PathVariable(value = "id") int userId) {
        if(userService.VerifyToken(request, userId))
            userRepository.deleteUserById((userId));
    }
}
