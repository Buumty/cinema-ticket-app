package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketRepository;
    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        this.ticketService = new TicketService(ticketRepository);
    }

    @Test
    public void givenNoTickets_whenFindAllTickets_thenReturnEmptyList() {
        when(ticketRepository.findAll()).thenReturn(emptyList());
        List<Ticket> allTickets = ticketService.findAllTickets();

        assertNotNull(allTickets);
        assertEquals(0, allTickets.size(), "The size of list should be 0 but is not!");
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void givenOneTicket_whenFindAllTickets_thenReturnListOfOne() {
        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket()));
        List<Ticket> allTickets = ticketService.findAllTickets();

        assertNotNull(allTickets);
        assertEquals(1, allTickets.size(), "The size of list should be 1 but is not!");
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void givenMultipleTickets_whenFindAllTickets_thenReturnCorrectList() {
        when(ticketRepository.findAll()).thenReturn(List.of(new Ticket(), new Ticket(), new Ticket()));
        List<Ticket> allTickets = ticketService.findAllTickets();

        assertNotNull(allTickets);
        assertEquals(3, allTickets.size(), "The size of list should be 3 but is not!");
        verify(ticketRepository, times(1)).findAll();
    }

    @Test
    public void givenCorrectId_whenFindTicketById_thenReturnCorrectTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setType("Normal");
        ticket.setPrice(10.00);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        Ticket ticketById = ticketService.findTicketById(1);

        assertNotNull(ticketById);
        assertEquals(1, ticketById.getId(), "The ticket ID should be 1 but is not!");
        assertEquals("Normal", ticketById.getType(), "The ticket type should be 'Normal' but is not!");
        assertEquals(10.00, ticketById.getPrice(), "The ticket price should be 10.00 but is not!");
        verify(ticketRepository, times(1)).findById(1);
    }

    @Test
    public void givenWrongId_whenFindTicketById_thenThrowCorrectException() {
        int wrongId = 1;

        when(ticketRepository.findById(wrongId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            ticketService.findTicketById(wrongId);
        });
        verify(ticketRepository, times(1)).findById(wrongId);
    }

    @Test
    public void givenTicket_whenCreateTicket_thenReturnCorrectTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setType("Normal");
        ticket.setPrice(10.00);

        when(ticketRepository.save(ticket)).thenReturn(ticket);
        Ticket createdTicket = ticketService.createTicket(ticket);

        assertNotNull(createdTicket);
        assertEquals(1, createdTicket.getId(), "The ticket ID should be 1 but is not!");
        assertEquals("Normal", createdTicket.getType(), "The ticket type should be 'Normal' but is not!");
        assertEquals(10.00, createdTicket.getPrice(), "The ticket price should be 10.00 but is not!");
        verify(ticketRepository, times(1)).save(ticket);
    }

    @Test
    public void givenNewTicket_whenUpdateTicket_thenReturnUpdatedTicket() {
        Ticket existingTicket = new Ticket();
        existingTicket.setId(1);
        existingTicket.setType("Normal");
        existingTicket.setPrice(10.00);

        Ticket newTicket = new Ticket();
        newTicket.setType("Kids");
        existingTicket.setPrice(7.50);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(existingTicket));
        when(ticketRepository.save(existingTicket)).thenReturn(existingTicket);
        Ticket updatedTicket = ticketService.updateTicket(newTicket, 1);

        assertNotNull(updatedTicket);
        assertEquals(1, updatedTicket.getId(), "The ticket ID should be 1 but is not!");
        assertEquals("Kids", updatedTicket.getType(), "The ticket type should be 'Kids' but is not!");
        assertEquals(7.50, updatedTicket.getPrice(), "The ticket price should be 7.50 but is not!");
        verify(ticketRepository, times(1)).save(existingTicket);
        verify(ticketRepository, times(1)).findById(1);
    }

    @Test
    public void givenCorrectId_whenDeleteTicketById_thenDeleteTicket() {
        Ticket ticket = new Ticket();
        ticket.setId(1);
        ticket.setType("Normal");
        ticket.setPrice(10.00);

        when(ticketRepository.findById(1)).thenReturn(Optional.of(ticket));
        ticketService.deleteTicketById(1);

        verify(ticketRepository, times(1)).findById(1);
        verify(ticketRepository, times(1)).deleteById(1);
    }

    @Test
    public void givenWrongId_whenDeleteTicketById_thenThrowCorrectException() {
        int wrongId = 1;

        when(ticketRepository.findById(wrongId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            ticketService.deleteTicketById(1);
        });
        verify(ticketRepository, times(1)).findById(wrongId);
    }
}
