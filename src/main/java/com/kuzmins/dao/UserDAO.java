package com.kuzmins.dao;

import com.kuzmins.model.Status;
import com.kuzmins.model.Ticket;
import com.kuzmins.model.TicketType;
import com.kuzmins.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(JdbcTemplate jdbcTemplate, TicketDAO ticketDAO) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final static String INSERT_USER_QUERY = "INSERT INTO user_info (id, name, creation_date, status) VALUES (?, ?, ?, ?::status)";
    private final static String SELECT_USER_BY_ID_QUERY = "SELECT * FROM user_info WHERE id=?";
    private final static String DELETE_USER_BY_ID_QUERY = "DELETE FROM user_info where id=?";
    private final static String UPDATE_USER_STATUS_QUERY = "UPDATE user_info set status=?::status WHERE id=?";
    private static final String SELECT_TICKETS_BY_USER_ID_QUERY = "SELECT * FROM ticket_info WHERE user_id=?";
    private static final String INSERT_TICKET_WITH_USER_QUERY = "INSERT INTO ticket_info (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";

    @Transactional
    public void saveUser(User user) {
        jdbcTemplate.update(INSERT_USER_QUERY, user.getId(), user.getName(), Timestamp.from(user.getCreationDate()), user.getStatus().name());
    }

    public User fetchUserById(UUID id) {
        User user = jdbcTemplate.query(SELECT_USER_BY_ID_QUERY, new UserMapper(), new Object[]{id})
                .stream().findAny().orElse(null);

        if (user != null) {
            List<Ticket> ticketList = jdbcTemplate.query(SELECT_TICKETS_BY_USER_ID_QUERY, new TicketMapper(), new Object[]{user.getId()});
            if (!ticketList.isEmpty()) {
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
        Ticket ticket = new Ticket(user.getId(), ticketType);
        user.getTickets().add(ticket);
        jdbcTemplate.update(INSERT_TICKET_WITH_USER_QUERY,
                ticket.getId(), user.getId(), ticket.getType().name(), Timestamp.from(ticket.getTicketCreationTime()));
        jdbcTemplate.update(UPDATE_USER_STATUS_QUERY, Status.ACTIVATED.name(), user.getId());
    }
}
