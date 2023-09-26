package com.userapi.model;


import jakarta.persistence.*;

@Entity
@Table(name="roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String name;

    public UserRole(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserRole() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
