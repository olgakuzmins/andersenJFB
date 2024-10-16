package com.kuzmins.model;

import java.util.UUID;

public class User extends BasicEntity {

    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". ");
    }
}
