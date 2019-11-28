package com.github.swapbook.repositories;

import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.FakeUserRepository;
import com.github.swapbook.repositories.users.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FakeUserRepositoryTest {

    @Test
    public void testAdd(){

        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 69;
        String name = "Omniom";
        User user = new User(id, name);

        // Act
        userRepository.addToList(user);
        User user2 = userRepository.getUserById(id);

        // Assert
        assertTrue(userRepository.getUsers().contains(user));
        assertEquals(name, user2.getName());
    }

}