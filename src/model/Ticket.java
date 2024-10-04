package model;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class Ticket extends BasicEntity{

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

    public Ticket(UUID id, String concertHall,
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

    public Sector getSector() {
        return sector;
    }

    public void checkId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("id is null");
        }
        this.id = id;
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

    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", concertHall='" + concertHall + '\'' +
                ", eventCode='" + eventCode + '\'' +
                ", time=" + time +
                ", isPromo=" + isPromo +
                ", sector=" + sector +
                ", backpackWeight=" + backpackWeight +
                ", ticketCreationTime=" + ticketCreationTime +
                ", price=" + price +
                '}';
    }
}

