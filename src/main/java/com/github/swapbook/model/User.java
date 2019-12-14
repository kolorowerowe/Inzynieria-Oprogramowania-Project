package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "swapbook.users")
public class User {

    @Id
    @PrimaryKeyJoinColumn
    @Column(columnDefinition = "user_id")
    private int user_id;

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "email")
    private String email;

    @Column(columnDefinition = "password")
    private String password;

    @Column(columnDefinition = "address")
    private String address;


    public int getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        //TODO
        //add some hash on password
        this.password = password;
    }

    public User() { }

    public User(int id, String name) {
        this.user_id = id;
        this.name = name;
    }

    public User(int id, String name, String email, String password, String address) {
        this.user_id = id;
        this.name = name;
        this.email = email;
        this.password = password; //TODO add hash
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return user_id == user.user_id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id);
    }

}
