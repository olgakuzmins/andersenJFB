package com.kuzmins.model.tickets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kuzmins.model.BasicEntity;
import com.kuzmins.model.users.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ticket_info")
@Getter
@Setter
public class Ticket extends BasicEntity {
  private static final DecimalFormat formatter = new DecimalFormat("€##,##");

  /* id parameter (PK name="id") is located in BasicEntity */

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User user;

  @Column(name = "ticket_type", nullable = false)
  @Enumerated(EnumType.STRING)
  private TicketType type;

  @Column(name = "creation_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Instant ticketCreationTime = Instant.now();

  // parameters which don't go to DB:

  @Transient private String concertHall;

  @Transient private String eventCode;

  @Transient private Instant time;

  @Transient private boolean isPromo;

  @Transient private Sector sector;

  @Transient private double backpackWeight;

  @Transient private BigDecimal price;

  public Ticket() {}

  public Ticket(User user, TicketType type) {
    this.user = user;
    this.type = type;
  }

  public Ticket(
      String concertHall,
      String eventCode,
      Instant time,
      boolean isPromo,
      Sector sector,
      TicketType type,
      double backpackWeight,
      BigDecimal price) {

    checkConcertHall(concertHall);
    checkEventCode(eventCode);
    this.time = time;
    this.isPromo = isPromo;
    this.sector = sector;
    this.type = type;
    this.backpackWeight = backpackWeight;
    this.price = price;
  }

  public boolean isPromo() {
    return isPromo;
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
        && Double.compare(backpackWeight, ticket.backpackWeight) == 0
        && Objects.equals(concertHall, ticket.concertHall)
        && Objects.equals(eventCode, ticket.eventCode)
        && Objects.equals(time, ticket.time)
        && sector == ticket.sector
        && type == ticket.type
        && Objects.equals(ticketCreationTime, ticket.ticketCreationTime)
        && Objects.equals(price, ticket.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        concertHall,
        eventCode,
        time,
        isPromo,
        sector,
        type,
        backpackWeight,
        ticketCreationTime,
        price);
  }

  @Override
  public String toString() {
    return "Ticket{"
        + "id="
        + id
        + ", price="
        + price
        + ", backpackWeight="
        + backpackWeight
        + ", sector="
        + sector
        + ", isPromo="
        + isPromo
        + ", time="
        + time
        + ", eventCode='"
        + eventCode
        + '\''
        + ", concertHall='"
        + concertHall
        + '\''
        + ", ticketCreationTime="
        + ticketCreationTime
        + ", type="
        + type
        + ", user="
        + (user == null ? "null" : user.getId())
        + '}';
  }

  private String formatDate(Instant instant) {
    LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    return dateTime.format(formatter);
  }
}
