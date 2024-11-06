package com.kuzmins;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kuzmins.config.SpringConfig;
import com.kuzmins.dao.TicketDAO;
import com.kuzmins.dao.UserDAO;
import com.kuzmins.model.BusTicket;
import com.kuzmins.model.TicketType;
import com.kuzmins.model.User;
import com.kuzmins.service.TicketDataReader;
import com.kuzmins.service.TicketService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringConfig.class);
        TicketService ticketService = applicationContext.getBean(TicketService.class);

        TicketDAO ticketDAO = applicationContext.getBean(TicketDAO.class);
        UserDAO userDAO = applicationContext.getBean(UserDAO.class);

        User matt = new User("Matt");
        userDAO.saveUser(matt);

        ticketService.enableUpdateUserAndCreateTicket(matt, TicketType.WEEK);

        TicketDataReader ticketDataReader = applicationContext.getBean(TicketDataReader.class);
        List<BusTicket> busTicketList = ticketDataReader.ticketsFromJSONDataFile();
    }
}
