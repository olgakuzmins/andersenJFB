package service;

import model.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

public class TicketService {

    private static final HashMap<UUID, Ticket> TICKETS = new HashMap<>();

    static {
        Ticket ticket1 = new Ticket(UUID.randomUUID(), "Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B,
                4.547, new BigDecimal("70.00"));
        TICKETS.put(ticket1.getId(), ticket1);

        Ticket ticket2 = new Ticket(UUID.randomUUID(), "Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), true, Sector.A,
                1.12, new BigDecimal("50.00"));
        TICKETS.put(ticket2.getId(), ticket2);

        Ticket ticket3 = new Ticket(UUID.randomUUID(), "Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), true, Sector.C,
                3.50, new BigDecimal("50.00"));
        TICKETS.put(ticket3.getId(), ticket3);

        Ticket ticket4 = new Ticket(UUID.randomUUID(), "Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B,
                2.569, new BigDecimal("70.00"));
        TICKETS.put(ticket4.getId(), ticket4);

        Ticket ticket5 = new Ticket(UUID.randomUUID(), "Radio City", "007",
                Instant.parse("2024-11-29T19:00:00.000Z"), false, Sector.A,
                1.975, new BigDecimal("90.00"));
        TICKETS.put(ticket5.getId(), ticket5);

        Ticket ticket6 = new Ticket(UUID.randomUUID(), "Ole Opry", "314",
                Instant.parse("2024-10-03T21:00:00.000Z"), true, Sector.C,
                12.311, new BigDecimal("50.00"));
        TICKETS.put(ticket6.getId(), ticket6);

        Ticket ticket7 = new Ticket(UUID.randomUUID(), "Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.C,
                0.000, new BigDecimal("50.00"));
        TICKETS.put(ticket7.getId(), ticket7);

        Ticket ticket8 = new Ticket(UUID.randomUUID(), "Tabernacle", "123",
                Instant.parse("2024-12-12T19:00:00.000Z"), true, Sector.B,
                9.150, new BigDecimal("70.00"));
        TICKETS.put(ticket8.getId(), ticket8);

        Ticket ticket9 = new Ticket(UUID.randomUUID(), "Red Rocks", "987",
                Instant.parse("2024-10-26T20:00:00.000Z"), false, Sector.A,
                1.312, new BigDecimal("90.00"));
        TICKETS.put(ticket9.getId(), ticket9);

        Ticket ticket10 = new Ticket(UUID.randomUUID(), "Ole Opry", "314",
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

    public void shareTicket(Ticket ticket, String phoneNumber) {
        System.out.println("The ticket " + checkTicket(ticket).getId() + " is sent to the number " + checkPhoneNumber(phoneNumber));
    }

    public void shareTicket(Ticket ticket, String phoneNumber, String email) {
        System.out.println("The ticket " + checkTicket(ticket).getId() + " is sent to the number " + checkPhoneNumber(phoneNumber) + " and email " + checkEmail(email));
    }

    public String checkPhoneNumber(String phoneNumber) {
        String number = phoneNumber;
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            number = number.replace(" ", "").
                    replace("(", "").
                    replace(")", "").
                    replace("-", "").
                    replace("+", "");
        } else {
            throw new IllegalArgumentException("Phone number shouldn't be empty");
        }

        if (number.matches("^\\d+")){
            return phoneNumber;
        } else throw new IllegalArgumentException("Phone number should consist of no more than 15 digits only");
    }

    public String checkEmail(String email) {
        if (email != null && email.matches("^[\\w-]+@[\\w-]+(\\.[\\w-]+)*\\.[a-z]{2,}$")) {
            return email;
        } else {
            throw new IllegalArgumentException("Wrong email format");
        }
    }

    private Ticket checkTicket(Ticket ticket) {
        if (ticket != null) {
            return ticket;
        } else throw new IllegalArgumentException("Ticket shouldn't be empty");
    }

    public static void main(String[] args) {
        TicketService service = new TicketService();
        List<Ticket> list = service.returnTicketsBySector(Sector.B);
        for (Ticket ticket : list) {
            ticket.print();
            service.shareTicket(ticket,"+38 098 76-09-65");
            service.shareTicket(ticket,"+38 098 76-09-65", "ujej@gmail.com");
        }

        User user = new User();
        user.printRole();

        Client client = new Client();
        client.printRole();

        Admin admin = new Admin();
        admin.printRole();

        Ticket ticket = new Ticket();
        System.out.println(client.getTicket());
        admin.checkTicket(ticket);
    }
}
