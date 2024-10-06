package model;


import java.util.ArrayList;
import java.util.List;

public class Client extends User {

    private final List<Ticket> userTickets = new ArrayList<>();

    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". It can 'buy' tickets, adding them to arraylist");
    }

    public List<Ticket> getTicket() {
        return userTickets;
    }
}
