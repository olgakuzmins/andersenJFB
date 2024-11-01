package com.kuzmins.dao;

import com.kuzmins.model.Status;
import com.kuzmins.model.Ticket;
import com.kuzmins.model.TicketType;
import com.kuzmins.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {
    private final JdbcTemplate jdbcTemplate;
    private final TicketDAO ticketDAO;

    @Value("${switcherForUpdateUserAndCreateTicket}")
    private String switcherForUpdateUserAndCreateTicket;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, TicketDAO ticketDAO) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketDAO = ticketDAO;
    }

    private final static String INSERT_USER_QUERY = "INSERT INTO user_info (id, name, creation_date, status) VALUES (?, ?, ?, ?::status)";
    private final static String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user_info WHERE id=?";
    private final static String DELETE_USER_BY_ID_QUERY = "DELETE FROM user_info where id=?";
    private final static String UPDATE_USER_STATUS_QUERY = "UPDATE user_info set status=?::status WHERE id=?";


    @Transactional
    public void saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getName(), Timestamp.from(user.getCreationDate()), user.getStatus().name());
    }

    public User fetchUserById(UUID id) {
        User user = jdbcTemplate.query(SELECT_USER_BY_ID_QUERY, new UserMapper(), new Object[] {id})
                .stream().findAny().orElse(null);

        if (user != null) {
            List<Ticket> ticketList = ticketDAO.fetchTicketsByUserId(user.getId());

            if (ticketList != null) {
                user.setTickets(ticketList);
            }
        }

        return user;
    }

    @Transactional
    public void deleteUserById(UUID userId) {
        jdbcTemplate.update(DELETE_USER_BY_ID_QUERY, userId);
    }

    @Transactional
    public void updateUserAndCreateTicket(User user, TicketType ticketType) {
        switch (switcherForUpdateUserAndCreateTicket.toUpperCase()){
            case "ON":
                Ticket ticket = new Ticket(user.getId(), ticketType);
                user.getTickets().add(ticket);
                ticketDAO.saveTicket(ticket, user);
                jdbcTemplate.update(UPDATE_USER_STATUS_QUERY, Status.ACTIVATED.name(), user.getId());
                break;
            case "OFF": throw new IllegalArgumentException("The operation is disabled now");
            default: throw new IllegalArgumentException("The operation is not enabled");
        }
    }
}
