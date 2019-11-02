package com.github.swapbook.repositories;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;
import com.github.swapbook.repositories.users.FakeUserRepository;
import com.github.swapbook.repositories.users.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

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

    @Test
    public void getUsers_ShouldReturnEmptyList() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();

        // Act
        List<User> userList = userRepository.getUsers();

        // Assert
        assertEquals(true, userList.isEmpty());
    }

    @Test
    public void getUsers_ShouldReturnAllUsers() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        userRepository.addToList(new User(1,"user1"));
        userRepository.addToList(new User(2,"user2"));
        userRepository.addToList(new User(3,"user3"));

        // Act
        List<User> userList = userRepository.getUsers();

        // Assert
        assertNotNull(userList);
        assertEquals(3, userList.size());
    }

    @Test
    public void getUserById_ShouldReturnNullForNorExistingUser() {
        // Arrange
        int id = 4;
        UserRepository userRepository = new FakeUserRepository();
        userRepository.addToList(new User(1,"user1"));
        userRepository.addToList(new User(2,"user2"));
        userRepository.addToList(new User(3,"user3"));

        // Act
        User user = userRepository.getUserById(id);

        // Assert
        assertNull(user);
    }

    @Test
    public void getUserById_ShouldReturnUser() {
        // Arrange
        int id = 3;
        String name = "user3";
        UserRepository userRepository = new FakeUserRepository();
        userRepository.addToList(new User(1,"user1"));
        userRepository.addToList(new User(2,"user2"));
        userRepository.addToList(new User(3,name));

        // Act
        User user = userRepository.getUserById(id);

        // Assert
        assertNotNull(user);
        assertEquals(user.getId(), id);
        assertEquals(user.getName(), name);
    }

    @Test
    public void addToList_ShouldNotAddNullToList() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();

        // Act
        userRepository.addToList(null);

        // Assert
        assertEquals(true, userRepository.getUsers().isEmpty());
    }

    @Test
    public void addToList_ShouldAddUserToList() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        String name = "user1";
        User user = new User(id, name);

        // Act
        userRepository.addToList(user);

        // Assert
        assertEquals(false, userRepository.getUsers().isEmpty());
        assertEquals(1, userRepository.getUsers().size());
    }

    @Test
    public void deleteUserById_ShouldNotDeleteIfUserNotExist() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        int idToRemove = 2;
        String name = "user1";
        User user = new User(id, name);
        userRepository.addToList(user);

        // Act
        userRepository.deleteUserById(idToRemove);

        // Assert
        assertEquals(false, userRepository.getUsers().isEmpty());
        assertEquals(1, userRepository.getUsers().size());
    }

    @Test
    public void deleteUserById_ShouldDeleteUser() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        String name = "user1";
        User user = new User(id, name);
        userRepository.addToList(user);

        // Act
        userRepository.deleteUserById(id);

        // Assert
        assertEquals(true, userRepository.getUsers().isEmpty());
    }

    @Test
    public void addSpecimen_ShouldNotAddNull() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        String name = "user1";
        User user = new User(id, name);
        userRepository.addToList(user);
        String specimenName = "ksiazka1";
        Specimen specimen = new Specimen(id, id, specimenName);

        // Act
        userRepository.addSpecimen(id, null);

        // Assert
        assertEquals(true, userRepository.getUserById(id).getSpecimenList().isEmpty());
    }

    @Test
    public void addSpecimen_ShouldAddSpecimen() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        String name = "user1";
        User user = new User(id, name);
        userRepository.addToList(user);
        String specimenName = "ksiazka1";
        Specimen specimen = new Specimen(id, id, specimenName);

        // Act
        userRepository.addSpecimen(id, specimen);

        // Assert
        assertEquals(false, userRepository.getUserById(id).getSpecimenList().isEmpty());
        assertEquals(userRepository.getUserById(id).getSpecimenList().size(), 1);
        assertTrue(userRepository.getUserById(id).getSpecimenList().contains(specimen));
    }

    @Test
    public void deleteSpecimen_ShouldNotDeleteNotExistingId() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        int idToRemove = 2;
        String name = "user1";
        User user = new User(id, name);
        userRepository.addToList(user);
        String specimenName = "ksiazka1";
        Specimen specimen = new Specimen(id, id, specimenName);
        userRepository.addSpecimen(id, specimen);

        // Act
        userRepository.deleteSpecimen(id, idToRemove);

        // Assert
        assertEquals(false, userRepository.getUserById(id).getSpecimenList().isEmpty());
        assertEquals(userRepository.getUserById(id).getSpecimenList().size(), 1);
        assertTrue(userRepository.getUserById(id).getSpecimenList().contains(specimen));
    }

    @Test
    public void deleteSpecimen_ShouldDeleteExistingId() {
        // Arrange
        UserRepository userRepository = new FakeUserRepository();
        int id = 1;
        int idToRemove = 2;
        String name = "user1";
        User user = new User(id, name);
        userRepository.addToList(user);
        String specimenName = "ksiazka1";
        Specimen specimen = new Specimen(id, id, specimenName);
        userRepository.addSpecimen(id, specimen);
        specimen = new Specimen(idToRemove, id, specimenName);

        // Act
        userRepository.deleteSpecimen(id, idToRemove);

        // Assert
        assertEquals(false, userRepository.getUserById(id).getSpecimenList().isEmpty());
        assertEquals(userRepository.getUserById(id).getSpecimenList().size(), 1);
        assertFalse(userRepository.getUserById(id).getSpecimenList().contains(specimen));
    }

}