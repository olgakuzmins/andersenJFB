package com.kuzmins.model;

import java.time.Instant;

public class User extends BasicEntity {

    private String name;
    private Instant creationDate;

    public User() {
        this.creationDate = Instant.now();
    }

    public User(String name) {
        this.name = name;
        this.creationDate = Instant.now();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }


    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". ");
    }
}
