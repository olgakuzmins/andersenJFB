package com.kuzmins.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.time.Instant;
import java.util.List;


@Entity
@Table(name = "user_info")
public class User extends BasicEntity {

    @Column(name="name")
    private String name;

    @Column(name="creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Instant creationDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "owner")
    @Cascade(CascadeType.ALL)
    private List<Ticket> tickets;

    public User() {
        this.creationDate = Instant.now();
    }

    public User(String name) {
        this.name = name;
        this.creationDate = Instant.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", tickets=" + tickets +
                '}';
    }

    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". ");
    }
}
