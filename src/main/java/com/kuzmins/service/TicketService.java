package com.kuzmins.service;

import com.kuzmins.model.*;
import com.kuzmins.storage.CustomArrayList;
import com.kuzmins.storage.CustomHashSet;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class TicketService extends BasicEntity implements ShareTicket {

    private static final HashMap<UUID, Ticket> TICKETS = new HashMap<>();

    static {
        Ticket ticket1 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B,
                4.547, new BigDecimal("70.00"));
        TICKETS.put(ticket1.getId(), ticket1);

        Ticket ticket2 = new Ticket("Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), true, Sector.A,
                1.12, new BigDecimal("50.00"));
        TICKETS.put(ticket2.getId(), ticket2);

        Ticket ticket3 = new Ticket("Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), true, Sector.C,
                3.50, new BigDecimal("50.00"));
        TICKETS.put(ticket3.getId(), ticket3);

        Ticket ticket4 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B,
                2.569, new BigDecimal("70.00"));
        TICKETS.put(ticket4.getId(), ticket4);

        Ticket ticket5 = new Ticket("Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), false, Sector.A,
                1.975, new BigDecimal("90.00"));
        TICKETS.put(ticket5.getId(), ticket5);

        Ticket ticket6 = new Ticket("Ole Opry", "314",
                Instant.parse("2024-10-03T21:00:00.000Z"), true, Sector.C,
                12.311, new BigDecimal("50.00"));
        TICKETS.put(ticket6.getId(), ticket6);

        Ticket ticket7 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.C,
                0.000, new BigDecimal("50.00"));
        TICKETS.put(ticket7.getId(), ticket7);

        Ticket ticket8 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.B,
                9.150, new BigDecimal("70.00"));
        TICKETS.put(ticket8.getId(), ticket8);

        Ticket ticket9 = new Ticket("Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), false, Sector.A,
                1.312, new BigDecimal("90.00"));
        TICKETS.put(ticket9.getId(), ticket9);

        Ticket ticket10 = new Ticket("Ole Opry", "314",
                Instant.parse("2024-10-03T21:00:00.000Z"), false, Sector.A,
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
        TicketService service = new TicketService();

/* CustomArrayList */

        CustomArrayList<Ticket> customArrayList = new CustomArrayList<>();

        //add(Ticket ticket)
        for (Ticket ticket : service.returnTicketsBySector(Sector.A)) {
            customArrayList.add(ticket);
            System.out.println(ticket);
        }

        //add(Ticket ticket, int index) + getByIndex(int index)
        Ticket ticket8 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.B,
                9.150, new BigDecimal("70.00"));

        customArrayList.add(ticket8, 3);
        System.out.println(customArrayList.getByIndex(3));

        //put(Ticket ticket, int index) + deleteByIndex(int index)
        Ticket ticket7 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.C,
                0.000, new BigDecimal("50.00"));

        customArrayList.put(ticket7, 0);
        System.out.println(customArrayList.getByIndex(0));
        customArrayList.deleteByIndex(0);
        System.out.println(customArrayList.getByIndex(0));


        Ticket[] tickets = new Ticket[3];
        List<Ticket> arrayList = service.returnTicketsBySector(Sector.B);
        System.arraycopy(arrayList.toArray(), 0, tickets, 0, tickets.length);

        //add(Ticket [] tickets)
        customArrayList.add(tickets);
        for (int i = 0; i < customArrayList.size(); i++) {
            System.out.println(customArrayList.getByIndex(i));
        }

        //add(Ticket [] tickets, int index)
        customArrayList.add(tickets, 0);
        for (int i = 0; i < customArrayList.size(); i++) {
            System.out.println(customArrayList.getByIndex(i));
        }

        //put(Ticket [] tickets, int index)
        customArrayList.put(tickets, 9);
        for (int i = 0; i < customArrayList.size(); i++) {
            System.out.println(customArrayList.getByIndex(i));
        }

/* CustomHashSet */

        CustomHashSet<Ticket> customHashSet = new CustomHashSet<>();

        Ticket ticket1 = new Ticket("Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B,
                4.547, new BigDecimal("70.00"));
        Ticket ticket2 = new Ticket("Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), true, Sector.A,
                1.12, new BigDecimal("50.00"));
        Ticket ticket3 = new Ticket("Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), true, Sector.C,
                3.50, new BigDecimal("50.00"));

        //put (T element)
        customHashSet.put(ticket1);
        customHashSet.put(ticket2);
        customHashSet.put(ticket3);

        //keep objects uniqueness
        customHashSet.put(ticket3);

        //iterate
        for (Ticket ticket : customHashSet) {
            System.out.println(ticket);
        }

        //delete(T element)
        customHashSet.delete(ticket1);

        //contains(T element)
        if (customHashSet.contains(ticket1)) {
            System.out.println("yes");
        } else System.out.println("no");

    }
}
