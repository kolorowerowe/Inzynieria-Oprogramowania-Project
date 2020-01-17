package com.github.swapbook.repositories.users;

import com.github.swapbook.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("swapbook.users")
public class UserDBRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public UserDBRepository() {
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createNativeQuery("select * from swapbook.users", User.class).getResultList();

    }

    @Override
    public User getUserById(int id) {
        return ((User) entityManager.createNativeQuery("select * from swapbook.users WHERE  user_id=?", User.class)
                .setParameter(1, id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void addToList(User user) {
        entityManager.createNativeQuery("INSERT INTO swapbook.users VALUES (?,?,?,?,?,?)")
                .setParameter(1, getNextID())
                .setParameter(2, user.getName())
                .setParameter(3, user.getEmail())
                .setParameter(4, user.getPassword())
                .setParameter(5, user.getAddress())
                .setParameter(6, user.getIsActive())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        entityManager.createNativeQuery("UPDATE swapbook.users SET name = ?, email = ?, password = ?, address = ?, is_active = ? WHERE user_id = ?", User.class)
                .setParameter(1, user.getName())
                .setParameter(2, user.getEmail())
                .setParameter(3, user.getPassword())
                .setParameter(4, user.getAddress())
                .setParameter(5, user.getIsActive())
                .setParameter(6, user.getUser_id())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
         entityManager.createNativeQuery("delete from swapbook.users WHERE  user_id=?", User.class)
                .setParameter(1, id)
                .executeUpdate();
    }

    @Override
    public User getUserByEmail(String email) {
        return ((User) entityManager.createNativeQuery("select * from swapbook.users WHERE  email=?", User.class)
                .setParameter(1, email)
                .getSingleResult());
    }

    public int getNextID() {
        int max = 0;
        List<User> userList = getUsers();

        for (User user : userList) {
            if (user.getUser_id() > max) {
                max = user.getUser_id();
            }
        }
        return max + 1;
    }

}
