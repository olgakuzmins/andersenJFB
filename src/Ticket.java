import java.math.BigDecimal;
import java.time.Instant;

public class Ticket {
    private String ID;
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

    public Ticket(String ID, String concertHall,
                  String eventCode, Instant time, boolean isPromo,
                  Sector sector, double backpackWeight, BigDecimal price) {
        checkID(ID);
        checkConcertHall(concertHall);
        checkEventCode(eventCode);
        this.time = time;
        this.isPromo = isPromo;
        this.sector = sector;
        this.backpackWeight = backpackWeight;
        this.price = price;
    }

    public void checkID(String ID) {
        if (ID != null) {
            if (!ID.isEmpty() && ID.length() <= 4) {
                if (ID.matches("^\\S+$")) {
                    this.ID = ID;
                } else {
                    throw new IllegalArgumentException("ID must not contain spaces");
                }
            } else {
                throw new IllegalArgumentException("ID must contain no more than 4 characters");
            }
        } else {
            throw new IllegalArgumentException("ID is null");
        }
    }

    public void checkConcertHall(String concertHall) {
        if (concertHall != null) {
            if (!concertHall.isEmpty() && concertHall.length() <= 10) {
                this.concertHall = concertHall;
            } else {
                throw new IllegalArgumentException("concertHall must contain no more than 10 characters");
            }
        } else {
            throw new IllegalArgumentException("concertHall is null");
        }
    }

    public void checkEventCode(String eventCode) {
        if (eventCode != null) {
            if (eventCode.matches("^\\d{3}")) {
                this.eventCode = eventCode;
            }
            else {
                throw new IllegalArgumentException("eventCode should consist of 3 digits only");
            }
        } else {
            throw new IllegalArgumentException("eventCode is null");
        }
    }
}

