package com.kuzmins.dao;

import com.kuzmins.config.ConnectionConfig;
import com.kuzmins.model.Ticket;
import com.kuzmins.model.TicketType;
import com.kuzmins.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TicketDAO {

    private static final Connection CONNECTION = ConnectionConfig.getConnection();
    private static final String INSERT_TICKET_QUERY = "INSERT INTO ticket_info (id, ticket_type, creation_date) VALUES (?, ?::ticket_type, ?)";
    private static final String INSERT_TICKET_WITH_USER_QUERY = "INSERT INTO ticket_info (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)";
    private static final String SELECT_TICKET_BY_ID_QUERY = "SELECT * FROM ticket_info WHERE id=?";
    private static final String SELECT_TICKETS_BY_USER_ID_QUERY = "SELECT * FROM ticket_info WHERE user_id=?";
    private static final String UPDATE_TICKET_TYPE_BY_ID_QUERY = "UPDATE ticket_info set ticket_type=?::ticket_type WHERE id=?";


    public void saveTicket
            (Ticket ticket) {
        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(INSERT_TICKET_QUERY);

            preparedStatement.setObject(1, ticket.getId());
            preparedStatement.setString(2, ticket.getType().name());
            preparedStatement.setTimestamp(3, Timestamp.from(ticket.getTicketCreationTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveTicket(Ticket ticket, User user) {
        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(INSERT_TICKET_WITH_USER_QUERY);

            preparedStatement.setObject(1, ticket.getId());
            preparedStatement.setObject(2, user.getId());
            preparedStatement.setString(3, ticket.getType().name());
            preparedStatement.setTimestamp(4, Timestamp.from(ticket.getTicketCreationTime()));

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Ticket fetchTicketById(UUID id) {
        Ticket ticket = null;
        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(SELECT_TICKET_BY_ID_QUERY);
            preparedStatement.setObject(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ticket = new Ticket();
                UUID ticketId = UUID.fromString(resultSet.getString("id"));
                ticket.setId(ticketId);
                if (resultSet.getString("user_id") != null) {
                    UUID userId = UUID.fromString(resultSet.getString("user_id"));
                    ticket.setUserId(userId);
                } else ticket.setUserId(null);
                ticket.setType((TicketType.valueOf(resultSet.getString("ticket_type"))));
                ticket.setTime(resultSet.getTimestamp("creation_date").toInstant());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return ticket;
    }

    public List<Ticket> fetchTicketsByUserId(UUID userId) {
        List<Ticket> listTicketOfUser = new ArrayList<>();

        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(SELECT_TICKETS_BY_USER_ID_QUERY);
            preparedStatement.setObject(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Ticket ticket = new Ticket();
                UUID ticketId = UUID.fromString(resultSet.getString("id"));
                ticket.setId(ticketId);
                UUID userUuid = UUID.fromString(resultSet.getString("user_id"));
                ticket.setUserId(userUuid);
                ticket.setType((TicketType.valueOf(resultSet.getString("ticket_type"))));
                ticket.setTime(resultSet.getTimestamp("creation_date").toInstant());
                listTicketOfUser.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listTicketOfUser;
    }

    public void updateTicketType(UUID id, TicketType type) {
        try {
            PreparedStatement preparedStatement =
                    CONNECTION.prepareStatement(UPDATE_TICKET_TYPE_BY_ID_QUERY);
            preparedStatement.setString(1, type.name());
            preparedStatement.setObject(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
