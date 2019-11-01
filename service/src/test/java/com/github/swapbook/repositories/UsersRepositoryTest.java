package com.github.swapbook.repositories;

import com.github.swapbook.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsersRepositoryTest {


    @Test
    public void testAdd(){
        UsersRepository usersRepository = new UsersRepository();

        int id = 69;
        String name = "Omniom";

        User user = new User(id, name);
        usersRepository.addToList(user);
        assertTrue(usersRepository.getUsers().contains(user));

        User user2 = usersRepository.getUserById(id);
        assertEquals(name, user2.getName());
    }

}