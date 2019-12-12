package com.github.swapbook.repositories.users;

import com.github.swapbook.model.Specimen;
import com.github.swapbook.model.User;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;


@Component
public class FakeUserRepository implements UserRepository {
    private List<User> listUsers = new LinkedList<>();

    public FakeUserRepository() {
        /*User user1 = new User(1, "name1");
        User user2 = new User(2, "name2");
        User user3 = new User(3, "name3");
        User user4 = new User(4, "name4", "123@123.pl", "123admin", "address");

        this.addToList(user1);
        this.addToList(user2);
        this.addToList(user3);
        this.addToList(user4);*/
    }

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
        if(user != null)
            listUsers.add(user);
    }

    @Override
    public void deleteUserById(int id) {
        listUsers.remove(getUserById(id));
    }

    @Override
    public void addSpecimen(int userId, Specimen specimen) {
        User user = getUserById(userId);
        if( user != null )
            if( specimen != null)
                user.addToSpecimenList(specimen);
    }

    @Override
    public void deleteSpecimen(int userId, int specimenId) {
        User user = getUserById(userId);
        if( user != null) {
            user.removeFromSpecimenList(specimenId);
        }
    }

    @Override
    public User getUserByEmail(String email) {
        return listUsers.stream()
                .filter(u -> u.getEmail().equals(email))
                .findAny()
                .orElse(null);
    }
}
