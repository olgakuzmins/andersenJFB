package com.kuzmins.service;

import com.kuzmins.dao.TicketDAO;
import com.kuzmins.dao.UserDAO;
import com.kuzmins.model.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class TicketService extends BasicEntity implements ShareTicket {

    private static final HashMap<UUID, Ticket> TICKETS = new HashMap<>();

    static {
        Ticket ticket1 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B, TicketType.WEEK,
                4.547, new BigDecimal("70.00"));
        TICKETS.put(ticket1.getId(), ticket1);

        Ticket ticket2 = new Ticket("Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), true, Sector.A, TicketType.DAY,
                1.12, new BigDecimal("50.00"));
        TICKETS.put(ticket2.getId(), ticket2);

        Ticket ticket3 = new Ticket("Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), true, Sector.C, TicketType.MONTH,
                3.50, new BigDecimal("50.00"));
        TICKETS.put(ticket3.getId(), ticket3);

        Ticket ticket4 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B, TicketType.YEAR,
                2.569, new BigDecimal("70.00"));
        TICKETS.put(ticket4.getId(), ticket4);

        Ticket ticket5 = new Ticket("Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), false, Sector.A, TicketType.DAY,
                1.975, new BigDecimal("90.00"));
        TICKETS.put(ticket5.getId(), ticket5);

        Ticket ticket6 = new Ticket("Ole Opry", "314",
                Instant.parse("2024-10-03T21:00:00.000Z"), true, Sector.C, TicketType.MONTH,
                12.311, new BigDecimal("50.00"));
        TICKETS.put(ticket6.getId(), ticket6);

        Ticket ticket7 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.C, TicketType.YEAR,
                0.000, new BigDecimal("50.00"));
        TICKETS.put(ticket7.getId(), ticket7);

        Ticket ticket8 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.B, TicketType.WEEK,
                9.150, new BigDecimal("70.00"));
        TICKETS.put(ticket8.getId(), ticket8);

        Ticket ticket9 = new Ticket("Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), false, Sector.A, TicketType.MONTH,
                1.312, new BigDecimal("90.00"));
        TICKETS.put(ticket9.getId(), ticket9);

        Ticket ticket10 = new Ticket("Ole Opry", "314",
                Instant.parse("2024-10-03T21:00:00.000Z"), false, Sector.A, TicketType.YEAR,
                0.500, new BigDecimal("90.00"));
        TICKETS.put(ticket10.getId(), ticket10);
    }

    public Ticket returnTicketById(UUID id) {
        return TICKETS.get(id);
    }

    public List<Ticket> returnTicketsBySector(Sector sector) {
        List<Ticket> sectorTickets = new ArrayList<>();
        for (Map.Entry<UUID, Ticket> ticket : TICKETS.entrySet()) {
            if (ticket.getValue().getSector().equals(sector)) {
                sectorTickets.add(ticket.getValue());
            }
        }
        return sectorTickets;
    }

    public static void main(String[] args) {

        TicketDAO ticketDAO = new TicketDAO();
        UserDAO userDAO = new UserDAO();

        Ticket ticket10 = new Ticket("Ole Opry", "314",
                Instant.parse("2024-10-03T21:00:00.000Z"), false, Sector.A, TicketType.YEAR,
                0.500, new BigDecimal("90.00"));
        Ticket ticket9 = new Ticket("Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), false, Sector.A, TicketType.MONTH,
                1.312, new BigDecimal("90.00"));
        Ticket ticket8 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.B, TicketType.WEEK,
                9.150, new BigDecimal("70.00"));
        Ticket ticket7 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.C, TicketType.YEAR,
                0.000, new BigDecimal("50.00"));

        User kate = new User("kate");
        User matt = new User("matt");

        ticketDAO.saveTicket(ticket10);
        userDAO.saveUser(kate);

        ticketDAO.saveTicket(ticket9, kate);
        ticketDAO.saveTicket(ticket8, kate);

        userDAO.saveUser(matt);
        ticketDAO.saveTicket(ticket7, matt);

        Ticket ticket = ticketDAO.fetchTicketById(ticket7.getId());

        List<Ticket> ticketList = ticketDAO.fetchTicketsByUserId(kate.getId());

        User user3 = userDAO.fetchUserById(matt.getId());

        ticketDAO.updateTicketType(ticket7.getId(), TicketType.MONTH);
        userDAO.deleteUserById(kate.getId());

    }
}
