package com.github.swapbook.repositories.users;


import com.github.swapbook.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById((long)id).orElse(null);
    }

    public void addToList(User user) {
        userRepository.save(user);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById((long) id);
    }

    public User getUserByEmail(String email) {

        return userRepository.findAll().stream().filter(u -> u.getEmail().equals(email)).findAny().orElse(null);
    }


}
