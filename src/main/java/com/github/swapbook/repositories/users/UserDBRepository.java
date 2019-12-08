package com.github.swapbook.repositories.users;

import com.github.swapbook.model.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository("Users")
public class UserDBRepository implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    NamedParameterJdbcTemplate template;

    public UserDBRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> getUsers() {
        return entityManager.createNativeQuery("select * from Users", User.class).getResultList();

    }

    @Override
    public User getUserById(int id) {
        return ((User) entityManager.createNativeQuery("select * from Users WHERE  id=?", User.class)
                .setParameter(1, id)
                .getSingleResult());
    }

    @Override
    @Transactional
    public void addToList(User user) {
        entityManager.createNativeQuery("INSERT INTO users VALUES (?,?,?,?,?)")
                .setParameter(1, user.getId())
                .setParameter(2, user.getName())
                .setParameter(3, user.getEmail())
                .setParameter(4, user.getPassword())
                .setParameter(5, user.getAddress())
                .executeUpdate();
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
         entityManager.createNativeQuery("delete from Users WHERE  id=?", User.class)
                .setParameter(1, id)
                .executeUpdate();
    }

}
