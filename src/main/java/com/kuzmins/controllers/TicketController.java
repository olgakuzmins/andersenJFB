package com.kuzmins.controllers;


import com.kuzmins.config.ConditionalBean;
import com.kuzmins.model.tickets.Ticket;
import com.kuzmins.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@AllArgsConstructor
public class TicketController {

    private final TicketService ticketService;
    private final ConditionalBean conditionalBean;

    @GetMapping("/tickets/{id}")
    public Ticket showTicket(@PathVariable("id") UUID id) {
        System.out.println(conditionalBean);
        return ticketService.findById(id);
    }
}
