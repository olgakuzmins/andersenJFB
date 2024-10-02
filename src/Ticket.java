import java.math.BigDecimal;
import java.time.Instant;

public class Ticket {
    private String id;
    private String concertHall;
    private String eventCode;
    private Instant time;
    private boolean isPromo;
    private Sector sector;
    private double backpackWeight;
    private Instant ticketCreationTime = Instant.now();
    private BigDecimal price;

    public Ticket() {
    }

    public Ticket(String concertHall, String eventCode, Instant time) {
        checkConcertHall(concertHall);
        checkEventCode(eventCode);
        this.time = time;
    }

    public Ticket(String id, String concertHall,
                  String eventCode, Instant time, boolean isPromo,
                  Sector sector, double backpackWeight, BigDecimal price) {
        checkId(id);
        checkConcertHall(concertHall);
        checkEventCode(eventCode);
        this.time = time;
        this.isPromo = isPromo;
        this.sector = sector;
        this.backpackWeight = backpackWeight;
        this.price = price;
    }

    public void checkId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }

        if (id.isEmpty() | id.length() > 4) {
            throw new IllegalArgumentException("id mustn't be empty or be longer than 4 characters");
        }

        if (id.matches("^\\S+$")) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("id must not contain spaces");
        }
    }

    public void checkConcertHall(String concertHall) {
        if (concertHall == null) {
            throw new IllegalArgumentException("concertHall is null");
        }

        if (!concertHall.isEmpty() && concertHall.length() <= 10) {
            this.concertHall = concertHall;
        } else {
            throw new IllegalArgumentException("concertHall must contain no more than 10 characters");
        }
    }

    public void checkEventCode(String eventCode) {
        if (eventCode == null) {
            throw new IllegalArgumentException("eventCode is null");
        }

        if (eventCode.matches("^\\d{3}")) {
            this.eventCode = eventCode;
        } else {
            throw new IllegalArgumentException("eventCode should consist of 3 digits only");
        }
    }
}

