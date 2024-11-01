package com.kuzmins.dao;

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
public class TicketDAO {

    private final JdbcTemplate jdbcTemplate;;

    @Autowired
    public TicketDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final String INSERT_TICKET_QUERY = "INSERT INTO ticket_info (id, ticket_type, creation_date) VALUES (?, ?::ticket_type, ?)";
    private static final String INSERT_TICKET_WITH_USER_QUERY = "INSERT INTO ticket_info (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
    private static final String SELECT_TICKET_BY_ID_QUERY = "SELECT * FROM ticket_info WHERE id=?";
    private static final String SELECT_TICKETS_BY_USER_ID_QUERY = "SELECT * FROM ticket_info WHERE user_id=?";
    private static final String UPDATE_TICKET_TYPE_BY_ID_QUERY = "UPDATE ticket_info set ticket_type=?::ticket_type WHERE id=?";
    private static final String SELECT_TICKETS_BY_ID_AND_USER_ID_QUERY = "SELECT * FROM ticket_info JOIN user_info ON user_info.id = ticket_info.user_id WHERE ticket_info.id=? and user_info.id=?";

    @Transactional
    public void saveTicket(Ticket ticket) {
        jdbcTemplate.update(INSERT_TICKET_QUERY,
                ticket.getId(), ticket.getType().name(), Timestamp.from(ticket.getTicketCreationTime()));
    }

    @Transactional
    public void saveTicket(Ticket ticket, User user) {
        jdbcTemplate.update(INSERT_TICKET_WITH_USER_QUERY,
                ticket.getId(), user.getId(), ticket.getType().name(), Timestamp.from(ticket.getTicketCreationTime()));
    }

    public Ticket fetchTicketById(UUID id) {
       return jdbcTemplate.query(SELECT_TICKET_BY_ID_QUERY, new TicketMapper(), new Object[] {id})
               .stream().findAny().orElse(null);
    }

    public List<Ticket> fetchTicketsByUserId(UUID userId) {
        List<Ticket> ticketList = jdbcTemplate.query(SELECT_TICKETS_BY_USER_ID_QUERY, new TicketMapper(), new Object[]{userId});
        if (ticketList.isEmpty()) {
            return null;
        } else return ticketList;
    }

    public Ticket fetchTicketByIdAndUserId(UUID id, UUID userId) {
        return jdbcTemplate.query(SELECT_TICKETS_BY_ID_AND_USER_ID_QUERY, new TicketMapper(), new Object[]{id, userId})
                .stream().findAny().orElse(null);
    }

    @Transactional
    public void updateTicketType(UUID id, TicketType type) {
        jdbcTemplate.update(UPDATE_TICKET_TYPE_BY_ID_QUERY, type.name(), id);
    }

}
