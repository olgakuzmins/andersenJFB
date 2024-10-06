package model;


public class Admin extends User {

    @Override
    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". It can 'check' if a ticket is not null");
    }
    public void checkTicket(Ticket ticket) {
        if (ticket == null) {
            throw new RuntimeException("Ticket can't be null");
        } else {
            System.out.println("The ticket is correct");
        }
    }
}
