package com.kuzmins.service;

import com.kuzmins.model.BasicEntity;
import com.kuzmins.model.ShareTicket;
import com.kuzmins.model.tickets.Ticket;
import com.kuzmins.model.tickets.TicketType;
import com.kuzmins.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class TicketService extends BasicEntity implements ShareTicket {

    private final TicketRepository ticketRepository;

    @Transactional
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public Ticket findById(UUID id) {
        Optional<Ticket> ticket = ticketRepository.findById(id);
        return ticket.orElse(null);
    }

    public List<Ticket> findByUserId(UUID userId) {
        return ticketRepository.findTicketsByUserId(userId);
    }

    public Ticket findByIdAndUserId(UUID id, UUID userId) {
        return ticketRepository.findTicketByIdAndUserId(id, userId);
    }

    @Transactional
    public void updateTicketType(UUID id, TicketType type) {
        Ticket ticket = findById(id);
        if (ticket != null) {
            ticket.setType(type);
            ticketRepository.save(ticket);
        }
    }
}
