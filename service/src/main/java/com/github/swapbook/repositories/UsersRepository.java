package com.github.swapbook.repositories;

import com.github.swapbook.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
public class UsersRepository {
    private List<User> listUsers = new LinkedList<>();

    public List<User> getUsers() {
        return listUsers;
    }

    public User getUserById(int id){
        return listUsers.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }

    public void addToList(User user){
        listUsers.add(user);
    }


}
