package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "Users")
public class User {

    @Id
    @PrimaryKeyJoinColumn
    @Column(columnDefinition = "id")
    private int id;

    @Column(columnDefinition = "name")
    private String name;

    @Column(columnDefinition = "email")
    private String email;

    @Column(columnDefinition = "password")
    private String password;

    @Column(columnDefinition = "address")
    private String address;


    public int getId() {
        return id;
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
        this.id = id;
        this.name = name;
    }

    public User(int id, String name, String email, String password, String address) {
        this.id = id;
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
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
