package service;

import model.Ticket;
import utils.CheckEmailUtil;
import utils.CheckPhoneNumberUtil;
import utils.CheckTicketUtil;

public interface ShareTicket {
    default void shareTicket(Ticket ticket, String phoneNumber) {
        System.out.println("The ticket " + CheckTicketUtil.checkTicket(ticket).getId() + " is sent to the number " + CheckPhoneNumberUtil.checkPhoneNumber(phoneNumber));
    }

    default void shareTicket(Ticket ticket, String phoneNumber, String email) {
        System.out.println("The ticket " + CheckTicketUtil.checkTicket(ticket).getId() + " is sent to the number " + CheckPhoneNumberUtil.checkPhoneNumber(phoneNumber) + " and email " + CheckEmailUtil.checkEmail(email));
    }
}
