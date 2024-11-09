package com.kuzmins.repositories;

import com.kuzmins.model.tickets.Ticket;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

  List<Ticket> findTicketsByUserId(UUID userId);

  Ticket findTicketByIdAndUserId(UUID id, UUID userId);
}
