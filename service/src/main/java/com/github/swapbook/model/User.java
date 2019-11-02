package com.github.swapbook.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private List<Specimen> specimenList;

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

    public List<Specimen> getSpecimenList() { return specimenList; }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        //add some hash on password
        this.password = password;
    }

    public void addToSpecimenList(Specimen specimen) {
        this.specimenList.add(specimen);
    }

    public void removeFromSpecimenList(int id) {
        Specimen sp = specimenList.stream().filter(s->s.getId() == id).findAny().orElse(null);

        this.specimenList.remove(sp);
    }

    public User(){}

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.specimenList = new LinkedList<>();
    }

    public User(int id, String name, String email, String password, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password; // add hash
        this.address = address;
        this.specimenList = new LinkedList<>();
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
