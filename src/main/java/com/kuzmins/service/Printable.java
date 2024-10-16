package com.kuzmins.service;

public interface Printable {

    default void print() {
        System.out.println("This is " + this.getClass().getSimpleName());
    }
}
