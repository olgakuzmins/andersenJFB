package model;

import utils.CheckTicketUtil;

public class Admin extends User {

    @Override
    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". It can 'check' if a ticket is not null");
    }

    public void checkTicket(Ticket ticket) {
        CheckTicketUtil.checkTicket(ticket);
        System.out.println("The ticket is correct");
    }
}
