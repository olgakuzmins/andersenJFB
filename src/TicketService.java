import java.math.BigDecimal;
import java.time.Instant;

public class TicketService {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Ticket ticket2 = new Ticket("k9!j", "Tabernacle", "123", Instant.parse("2024-12-12T19:00:00.000Z"), false, Sector.B, 4.547, new BigDecimal("70.00"));
        Ticket ticket3 = new Ticket("Radio City", "007", Instant.parse("2024-12-12T19:00:00.000Z"));
    }
}
