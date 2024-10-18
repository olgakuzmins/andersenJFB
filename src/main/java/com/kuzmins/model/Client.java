package com.kuzmins.model;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client extends User {

    private final List<Ticket> userTickets = new ArrayList<>();

    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". It can 'buy' tickets, adding them to arraylist");
    }

    public List<Ticket> getTicket() {
        return userTickets;
    }
}
