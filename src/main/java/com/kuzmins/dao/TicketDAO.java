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

    private final static Connection connection = ConnectionConfig.getConnection();

    public void saveTicket(Ticket ticket) {
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO tickets (id, ticket_type, creation_date) VALUES (?, ?::ticket_type, ?)");

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
                    connection.prepareStatement("INSERT INTO tickets (id, user_id, ticket_type, creation_date) VALUES (?, ?, ?::ticket_type, ?)");

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
                    connection.prepareStatement("SELECT * FROM tickets WHERE id=?");
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
                    connection.prepareStatement("SELECT * FROM tickets WHERE user_id=?");
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
                    connection.prepareStatement("UPDATE tickets set ticket_type=?::ticket_type WHERE id=?");
            preparedStatement.setString(1, type.name());
            preparedStatement.setObject(2, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
