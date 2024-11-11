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
import static org.mockito.ArgumentMatchers.eq;
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

    Mockito.verify(ticketRepository).save(eq(ticket));
    Assertions.assertEquals("Problem with DB access", runtimeException.getMessage());

    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  // Ticket findById(UUID id)
  @Test
  void findTicketWhenCallFindById() {
    UUID id = UUID.randomUUID();
    Ticket expectedTicket = new Ticket();

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.of(expectedTicket));
    Ticket actualTicket = ticketService.findById(id);

    Assertions.assertEquals(expectedTicket, actualTicket);

    Mockito.verify(ticketRepository).findById(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotFindTicketWhenCallFindById() {
    UUID id = UUID.randomUUID();

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.empty());
    Ticket actualTicket = ticketService.findById(id);

    Assertions.assertNull(actualTicket);
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
    List<Ticket> expectedTicketList = new ArrayList<>();
    expectedTicketList.add(new Ticket());

    Mockito.when(ticketRepository.findTicketsByUserId(id)).thenReturn(expectedTicketList);
    List<Ticket> actualTicketList = ticketService.findByUserId(id);

    Assertions.assertEquals(expectedTicketList, actualTicketList);

    Mockito.verify(ticketRepository).findTicketsByUserId(id);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotFindsTicketWhenCallFindByUserId() {
    UUID id = UUID.randomUUID();

    Mockito.when(ticketRepository.findTicketsByUserId(id)).thenReturn(null);
    List<Ticket> actualTicketList = ticketService.findByUserId(id);

    Assertions.assertNull(actualTicketList);

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

    Ticket expectedTicket = new Ticket();
    User user = new User();

    user.setId(userId);
    expectedTicket.setId(id);
    expectedTicket.setUser(user);

    Mockito.when(ticketRepository.findTicketByIdAndUserId(id, userId)).thenReturn(expectedTicket);

    Ticket actualTicket = ticketService.findByIdAndUserId(id, userId);

    Assertions.assertEquals(expectedTicket, actualTicket);

    Mockito.verify(ticketRepository).findTicketByIdAndUserId(id, userId);
    Mockito.verifyNoMoreInteractions(ticketRepository);
  }

  @Test
  void doNotFindTicketWhenCallFindByIdAndUserId() {
    UUID id = UUID.randomUUID();
    UUID userId = UUID.randomUUID();

    Mockito.when(ticketRepository.findTicketByIdAndUserId(id, userId)).thenReturn(null);
    Ticket actualTicket = ticketService.findByIdAndUserId(id, userId);

    Assertions.assertNull(actualTicket);

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
    Ticket expectedTicket = new Ticket(new User(), TicketType.MONTH);

    Mockito.when(ticketRepository.findById(id)).thenReturn(Optional.of(expectedTicket));

    ticketService.updateTicketType(id, ticketType);

    ArgumentCaptor<Ticket> argumentCaptor = ArgumentCaptor.forClass(Ticket.class);
    Mockito.verify(ticketRepository).save(argumentCaptor.capture());
    Ticket actualTicket = argumentCaptor.getValue();

    Assertions.assertEquals(expectedTicket.getId(), actualTicket.getId());
    Assertions.assertEquals(expectedTicket.getUser(), actualTicket.getUser());
    Assertions.assertEquals(expectedTicket.getTicketCreationTime(), actualTicket.getTicketCreationTime());
    Assertions.assertEquals(ticketType, actualTicket.getType());

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
    Mockito.verify(ticketRepository, Mockito.never()).save(any(Ticket.class));
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
