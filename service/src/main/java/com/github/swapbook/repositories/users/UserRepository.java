package com.github.swapbook.repositories.users;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;

import java.util.List;

public interface UserRepository {

    public List<User> getUsers();
    public User getUserById(int id);
    public void addToList(User user);
    public void deleteUserById(int id);
    public void addSpecimen(int userId, Specimen specimen);
    public void deleteSpecimen(int userId, int specimenId);
}
