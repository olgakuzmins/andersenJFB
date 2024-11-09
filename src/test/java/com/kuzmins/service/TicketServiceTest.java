package com.kuzmins.service;

import static org.mockito.ArgumentMatchers.any;

import com.kuzmins.model.tickets.Ticket;
import com.kuzmins.model.tickets.TicketType;
import com.kuzmins.model.users.User;
import com.kuzmins.repositories.TicketRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {

  @Mock private TicketRepository ticketRepository;

  @InjectMocks private TicketService ticketService;

  // void save(Ticket ticket)
  @Test
  void callSaveTicketRepositoryWhenCallSave() {
    User user = new User();
    Ticket ticket = new Ticket(user, TicketType.MONTH);

    ticketService.save(ticket);

    Mockito.verify(ticketRepository).save(ticket);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doesNotSaveNullWhenCallSave() {
    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticketService.save(null));

    Assertions.assertNotNull(illegalArgumentException);
    Assertions.assertEquals("Ticket is null", illegalArgumentException.getMessage());

    Mockito.verify(ticketRepository, Mockito.never()).save(null);
    Mockito.verifyNoInteractions(ticketRepository);
  }

  @Test
  void doesNotSaveInCaseExceptionWhenCallSave() {
    User user = new User();
    Ticket ticket = new Ticket(user, TicketType.MONTH);

    Mockito.when(ticketRepository.save(ticket))
        .thenThrow(new RuntimeException("Problem with DB access"));
    RuntimeException runtimeException =
        Assertions.assertThrows(RuntimeException.class, () -> ticketService.save(ticket));

    Assertions.assertNotNull(runtimeException);

    Mockito.verify(ticketRepository).save(any(Ticket.class));
    Assertions.assertEquals("Problem with DB access", runtimeException.getMessage());

    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  // Ticket findById(UUID id)
  @Test
  void findTicketWhenCallFindById() {
    UUID id = UUID.randomUUID();
    Ticket ticket = new Ticket();

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));
    Ticket byId = ticketService.findById(id);

    Assertions.assertEquals(ticket, byId);

    Mockito.verify(ticketRepository).findById(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotFindTicketWhenCallFindById() {
    UUID id = UUID.randomUUID();

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.empty());
    Ticket byId = ticketService.findById(id);

    Assertions.assertNull(byId);
    Mockito.verify(ticketRepository).findById(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void throwExceptionWhenCallFindById() {
    UUID id = null;

    Mockito.when(ticketRepository.findById(id))
        .thenThrow(new IllegalArgumentException("id is null"));

    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(IllegalArgumentException.class, () -> ticketService.findById(id));

    Assertions.assertNotNull(illegalArgumentException);
    Assertions.assertEquals("id is null", illegalArgumentException.getMessage());

    Mockito.verify(ticketRepository).findById(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  // List<Ticket> findByUserId(UUID userId)
  @Test
  void findsListOfTicketsWhenCallFindByUserId() {
    UUID id = UUID.randomUUID();
    List<Ticket> ticketList = new ArrayList<>();
    ticketList.add(new Ticket());

    Mockito.when(ticketRepository.findTicketsByUserId(id)).thenReturn(ticketList);
    List<Ticket> returnedListOfTickets = ticketService.findByUserId(id);

    Assertions.assertEquals(ticketList, returnedListOfTickets);

    Mockito.verify(ticketRepository).findTicketsByUserId(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotFindsTicketWhenCallFindByUserId() {
    UUID id = UUID.randomUUID();

    Mockito.when(ticketRepository.findTicketsByUserId(id)).thenReturn(null);
    List<Ticket> returnedListOfTickets = ticketService.findByUserId(id);

    Assertions.assertNull(returnedListOfTickets);

    Mockito.verify(ticketRepository).findTicketsByUserId(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void throwExceptionWhenCallsFindByUserId() {
    UUID id = null;

    Mockito.when(ticketRepository.findTicketsByUserId(id))
        .thenThrow(new IllegalArgumentException("id is null"));

    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> ticketService.findByUserId(id));

    Assertions.assertNotNull(illegalArgumentException);
    Assertions.assertEquals("id is null", illegalArgumentException.getMessage());

    Mockito.verify(ticketRepository).findTicketsByUserId(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  // Ticket findByIdAndUserId(UUID id, UUID userId)
  @Test
  void findTicketWhenCallFindByIdAndUserId() {
    UUID id = UUID.randomUUID();
    UUID userId = UUID.randomUUID();

    Ticket ticket = new Ticket();
    User user = new User();

    user.setId(userId);
    ticket.setId(id);
    ticket.setUser(user);

    Mockito.when(ticketRepository.findTicketByIdAndUserId(id, userId)).thenReturn(ticket);

    Ticket byId = ticketService.findByIdAndUserId(id, userId);

    Assertions.assertEquals(ticket, byId);

    Mockito.verify(ticketRepository).findTicketByIdAndUserId(id, userId);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotFindTicketWhenCallFindByIdAndUserId() {
    UUID id = UUID.randomUUID();
    UUID userId = UUID.randomUUID();
    Ticket ticket = new Ticket();

    Mockito.when(ticketRepository.findTicketByIdAndUserId(id, userId)).thenReturn(null);
    Ticket byId = ticketService.findByIdAndUserId(id, userId);

    Assertions.assertNull(byId);

    Mockito.verify(ticketRepository).findTicketByIdAndUserId(id, userId);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void throwExceptionWhenCallFindByIdAndUserId() {
    UUID id = null;
    UUID userId = null;

    Mockito.when(ticketRepository.findTicketByIdAndUserId(id, userId))
        .thenThrow(new IllegalArgumentException("id is null"));

    IllegalArgumentException illegalArgumentException =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> ticketService.findByIdAndUserId(id, userId));

    Assertions.assertNotNull(illegalArgumentException);
    Assertions.assertEquals("id is null", illegalArgumentException.getMessage());

    Mockito.verify(ticketRepository).findTicketByIdAndUserId(id, userId);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  // ticketRepository.updateTicketType()
  @Test
  void updateTicketWhenCallUpdateTicketType() {
    UUID id = UUID.randomUUID();
    TicketType ticketType = TicketType.YEAR;
    Ticket ticket = new Ticket(new User(), TicketType.MONTH);

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.of(ticket));

    ticketService.updateTicketType(id, ticketType);

    ArgumentCaptor<Ticket> argumentCaptor = ArgumentCaptor.forClass(Ticket.class);
    Mockito.verify(ticketRepository).save(argumentCaptor.capture());
    Ticket ticket1 = argumentCaptor.getValue();

    Assertions.assertEquals(ticket.getId(), ticket1.getId());
    Assertions.assertEquals(ticket.getUser(), ticket1.getUser());
    Assertions.assertEquals(ticket.getTicketCreationTime(), ticket1.getTicketCreationTime());
    Assertions.assertEquals(ticketType, ticket1.getType());

    Mockito.verify(ticketRepository).findById(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotUpdateWhenCallUpdateTicketType() {
    UUID id = UUID.randomUUID();
    TicketType ticketType = TicketType.YEAR;

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.empty());

    ticketService.updateTicketType(id, ticketType);

    Mockito.verify(ticketRepository).findById(id);
    Mockito.verify(ticketRepository, Mockito.never()).save(any());
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void throwExceptionsWhenCallUpdateTicketType() {
    UUID id = UUID.randomUUID();
    TicketType ticketType = null;

    RuntimeException runtimeException =
        Assertions.assertThrows(
            RuntimeException.class, () -> ticketService.updateTicketType(id, ticketType));

    Assertions.assertNotNull(runtimeException);
    Assertions.assertEquals(
        "DataBase is unable to save null in the field ticket_type", runtimeException.getMessage());

    Mockito.verify(ticketRepository, Mockito.never()).save(any(Ticket.class));
    Mockito.verifyNoInteractions(ticketRepository);
  }
}
