package com.kuzmins.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

public class Ticket extends BasicEntity {
    private static final DecimalFormat formatter = new DecimalFormat("€##,##");

    private UUID userId;
    private String concertHall;
    private String eventCode;
    private Instant time;
    private boolean isPromo;
    private Sector sector;
    private TicketType type;
    private double backpackWeight;
    private Instant ticketCreationTime = Instant.now();
    private BigDecimal price;

    public Ticket() {
    }

    public Ticket(UUID userId, TicketType type) {
        this.userId = userId;
        this.type = type;
    }

    public Ticket(String concertHall,
                  String eventCode, Instant time, boolean isPromo,
                  Sector sector, TicketType type, double backpackWeight, BigDecimal price) {

        checkConcertHall(concertHall);
        checkEventCode(eventCode);
        this.time = time;
        this.isPromo = isPromo;
        this.sector = sector;
        this.type = type;
        this.backpackWeight = backpackWeight;
        this.price = price;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getConcertHall() {
        return concertHall;
    }

    public String getEventCode() {
        return eventCode;
    }

    public Instant getTime() {
        return time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public boolean isPromo() {
        return isPromo;
    }

    public double getBackpackWeight() {
        return backpackWeight;
    }

    public Instant getTicketCreationTime() {
        return ticketCreationTime;
    }

    public void setTicketCreationTime(Instant ticketCreationTime) {
        this.ticketCreationTime = ticketCreationTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public TicketType getType() {
        return type;
    }

    public void setType(TicketType type) {
        this.type = type;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if ((object == null) || (getClass() != object.getClass())) return false;
        Ticket ticket = (Ticket) object;
        return isPromo == ticket.isPromo
                && Double.compare(backpackWeight, ticket.backpackWeight) == 0 && Objects.equals(concertHall, ticket.concertHall)
                && Objects.equals(eventCode, ticket.eventCode) && Objects.equals(time, ticket.time)
                && sector == ticket.sector && type == ticket.type
                && Objects.equals(ticketCreationTime, ticket.ticketCreationTime)
                && Objects.equals(price, ticket.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(concertHall, eventCode, time, isPromo, sector, type, backpackWeight, ticketCreationTime, price);
    }

    @Override
    public String toString() {
        return "Ticket #" + id + '\n' +
                "concertHall = "+ concertHall + '\n' +
                "eventCode = " + eventCode + '\n' +
                "time = " + formatDate(time) + '\n' +
                "isPromo = " + isPromo + '\n' +
                "sector = " + sector + '\n' +
                "type = " + type + '\n' +
                "backpackWeight = " + backpackWeight + '\n' +
                "ticketCreationTime = " + formatDate(ticketCreationTime) + '\n' +
                "price = " + (price != null ? formatter.format(price) : null) + '\n';
    }

    private String formatDate(Instant instant) {
        LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(formatter);
    }
}

