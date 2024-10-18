package com.kuzmins.service;

import com.kuzmins.model.Ticket;
import com.kuzmins.utils.CheckEmailUtil;
import com.kuzmins.utils.CheckPhoneNumberUtil;
import com.kuzmins.utils.CheckTicketUtil;


public interface ShareTicket {
    default void shareTicket(Ticket ticket, String phoneNumber) {
        System.out.println("The ticket " + CheckTicketUtil.checkTicket(ticket).getId() + " is sent to the number " + CheckPhoneNumberUtil.checkPhoneNumber(phoneNumber));
    }

    default void shareTicket(Ticket ticket, String phoneNumber, String email) {
        System.out.println("The ticket " + CheckTicketUtil.checkTicket(ticket).getId() + " is sent to the number " + CheckPhoneNumberUtil.checkPhoneNumber(phoneNumber) + " and email " + CheckEmailUtil.checkEmail(email));
    }
}
