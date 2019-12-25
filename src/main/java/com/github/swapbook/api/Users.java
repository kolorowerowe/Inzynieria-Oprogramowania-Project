package com.github.swapbook.api;

import com.github.swapbook.email.EmailService;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.UserDBRepository;
import com.github.swapbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class Users {
    //@Autowired
    UserDBRepository userRepository;

    UserService userService;

    EmailService emailService;

    @Autowired
    public Users (UserDBRepository userRepository,
                  UserService userService, EmailService emailService)
    {
        this.userRepository = userRepository;
        this.userService = userService;
        this.emailService = emailService;
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
    public void createUser(@RequestBody User user) throws MessagingException {
        user.setIsActive(false);
        emailService.sendMessage(user.getEmail(), "Create user in SwapBook", "<html><body>Confirm Your account. <button><a href=\"http://localhost:8080/users/confirm/"+user.getUser_id()+"\">Confirm</a></button> <h3>Team swapBook</h3></body></html>", true);

        userRepository.addToList(user);
    }

    @GetMapping("/api/users/confirm/{id}")
    public void confirmCreateUser(@PathVariable(value = "id") int userId) {
        User user = userRepository.getUserById(userId);
        user.setIsActive(true);
    }

    @DeleteMapping("/api/users/{id}")
    public void deleteUser(HttpServletRequest request, @PathVariable(value = "id") int userId) {
        if(userService.VerifyToken(request, userId)) {
            //userRepository.deleteUserById((userId));
            User user = userRepository.getUserById(userId);
            user.setIsActive(false);
        }
    }
}
