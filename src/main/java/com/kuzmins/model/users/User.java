package com.kuzmins.model.users;

import com.kuzmins.model.BasicEntity;
import com.kuzmins.model.tickets.Ticket;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "user_info")
@Setter
@Getter
public class User extends BasicEntity {

  /* id parameter (PK name="id") is located in BasicEntity */

  @Column(name = "name")
  private String name;

  @Column(name = "creation_date", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Instant creationDate = Instant.now();

  @Column(name = "status", nullable = false)
  @Enumerated(EnumType.STRING)
  private Status status = Status.MUTED;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
  @Cascade(CascadeType.ALL)
  private List<Ticket> tickets = new ArrayList<>();

  @Column(name = "password")
  private String password = "1234";

  public User() {}

  public User(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", tickets="
        + tickets
        + ", status="
        + status
        + ", creationDate="
        + creationDate
        + ", name='"
        + name
        + '\''
        + '}';
  }

  public void printRole() {
    System.out.println("This User is " + this.getClass().getSimpleName() + ". ");
  }
}
