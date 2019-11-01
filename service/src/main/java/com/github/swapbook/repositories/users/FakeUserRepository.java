package com.github.swapbook.repositories.users;

import com.github.swapbook.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
public class FakeUserRepository implements UserRepository {
    private List<User> listUsers = new LinkedList<>();

    @Override
    public List<User> getUsers() {
        return listUsers;
    }

    @Override
    public User getUserById(int id){
        return listUsers.stream()
                .filter(u -> u.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public void addToList(User user){
        listUsers.add(user);
    }


}
