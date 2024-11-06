package com.kuzmins.service;

import com.kuzmins.model.tickets.Ticket;
import com.kuzmins.model.tickets.TicketType;
import com.kuzmins.model.users.User;
import com.kuzmins.repositories.TicketRepository;
import com.kuzmins.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
public class UserService{

    @Value("${switcherForUpdateUserAndCreateTicket}")
    private String switcherForUpdateUserAndCreateTicket;

    private UserRepository userRepository;
    private TicketRepository ticketRepository;

    @Autowired
    public UserService(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    public User findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Transactional
    public void update(UUID id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    @Transactional
    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public void enableUpdateUserAndCreateTicket(User user, TicketType ticketType) {
        switch (switcherForUpdateUserAndCreateTicket.toUpperCase()){
            case "ON":
                update(user.getId(), user);
                ticketRepository.save(new Ticket(user, ticketType));
                break;
            case "OFF": throw new IllegalArgumentException("The operation is disabled now");
            default: throw new IllegalArgumentException("The operation is not enabled");
        }
    }
}
