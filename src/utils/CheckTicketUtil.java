package utils;

import model.Ticket;

public class CheckTicketUtil {

    private CheckTicketUtil(){}

    public static Ticket checkTicket(Ticket ticket) {
        if (ticket != null) {
            return ticket;
        } else throw new RuntimeException("Ticket can't be null");
    }
}
