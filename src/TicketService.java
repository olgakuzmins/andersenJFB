import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;

public class TicketService {
    public static void main(String[] args) {
        ArrayList<Ticket> tickets = new ArrayList<>();

        BigDecimal price = new BigDecimal("60.55");
        tickets.add(new Ticket("111A", "Opera", "121", Instant.now(), false, Sector.A, (float) 5.00, price));
        tickets.add(new Ticket("112A", "Opera", "122", Instant.now(), false, Sector.A, (float) 5.00, price));
        tickets.add(new Ticket("113A", "Opera", "123", Instant.now(), false, Sector.A, (float) 5.00, price));
        tickets.add(new Ticket("120B", "Opera", "222", Instant.now(), false, Sector.B, (float) 5.00, price));
        tickets.add(new Ticket("121B", "Opera", "223", Instant.now(), false, Sector.B, (float) 5.00, price));
        tickets.add(new Ticket("122B", "Opera", "224", Instant.now(), false, Sector.B, (float) 5.00, price));
        tickets.add(new Ticket("131C", "Opera", "331", Instant.now(), false, Sector.C, (float) 5.00, price));
        tickets.add(new Ticket("132C", "Opera", "332", Instant.now(), false, Sector.C, (float) 5.00, price));
        tickets.add(new Ticket("133C", "Opera", "333", Instant.now(), false, Sector.C, (float) 5.00, price));
        tickets.add(new Ticket("134C", "Opera", "334", Instant.now(), false, Sector.C, (float) 5.00, price));

        for (int i = 0; i < tickets.size(); i++) {
            Ticket ticket = tickets.get(i);
            System.out.println(tickets.get(i));
        }
        Ticket ticket = findByID(tickets, "333");
        System.out.println(ticket);

        ArrayList<Ticket> stadium = findBySector(tickets, Sector.B);
        System.out.println(stadium);
    }

    // Method to return a ticket by ID
    public static Ticket findByID(ArrayList<Ticket> tickets, String id) {
        for (Ticket ticket : tickets) {
            if (ticket.getId().equals(id)) {
                return ticket; // Return the matching ticket
            }
        }
        return null; // Return null if no ticket is found
    }
    public static ArrayList<Ticket> findBySector(ArrayList<Ticket> tickets, Sector sector){
        ArrayList<Ticket> foundTickets = new ArrayList<>();

        for (Ticket ticket : tickets){
            if (ticket.getSector().equals(sector)) {
                foundTickets.add(ticket);
            }
        }
        return foundTickets;
    }

}
