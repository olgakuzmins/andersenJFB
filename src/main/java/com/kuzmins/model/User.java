package com.kuzmins.model;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class User extends BasicEntity {

    private String name;
    private Instant creationDate = Instant.now();
    private Status status = Status.MUTED;
    private List<Ticket> tickets = new ArrayList<>();

    public User() {}

    public User(String name) {
        this.name = name;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
                ", tickets=" + tickets +
                ", status=" + status +
                ", creationDate=" + creationDate +
                ", name='" + name + '\'' +
                '}';
    }

    public void printRole() {
        System.out.println("This User is " + this.getClass().getSimpleName() + ". ");
    }
}
