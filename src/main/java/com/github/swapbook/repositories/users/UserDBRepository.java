package com.github.swapbook.repositories.users;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.swapbook.model.Specimen;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.github.swapbook.model.User;

import org.springframework.stereotype.Repository;

@Repository("Users")
public class UserDBRepository implements UserRepository{

    NamedParameterJdbcTemplate template;

    public UserDBRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<User> getUsers() {
        return template.query("select * from Users", new UserDBMapper());

    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public void addToList(User user) {
        final String sql = "insert into Users(id, name , email,password, address, specimenList) values(:id,:name,:email,:password, :address, :specimenList)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", user.getId())
                .addValue("name", user.getName())
                .addValue("email", user.getEmail())
                .addValue("password", user.getPassword())
                .addValue("address", user.getAddress())
                .addValue("specimenList", user.getSpecimenList().toString());
        template.update(sql,param, holder);
    }

    @Override
    public void deleteUserById(int id) {

    }

    @Override
    public void addSpecimen(int userId, Specimen specimen) {

    }

    @Override
    public void deleteSpecimen(int userId, int specimenId) {

    }
}
