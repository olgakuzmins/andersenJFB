package com.kuzmins.dao;

import com.kuzmins.model.Ticket;
import com.kuzmins.model.TicketType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TicketMapper implements RowMapper<Ticket> {

    @Override
    public Ticket mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(UUID.fromString(resultSet.getString("id")));
        if (resultSet.getString("user_id") != null) {
            UUID userId = UUID.fromString(resultSet.getString("user_id"));
            ticket.setUserId(userId);
        } else ticket.setUserId(null);
        ticket.setType((TicketType.valueOf(resultSet.getString("ticket_type"))));
        ticket.setTicketCreationTime(resultSet.getTimestamp("creation_date").toInstant());
        return ticket;
    }
}
