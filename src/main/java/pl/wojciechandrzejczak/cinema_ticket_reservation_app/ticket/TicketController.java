package pl.wojciechandrzejczak.cinema_ticket_reservation_app.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/ticket")
    public ResponseEntity<List<Ticket>> findAllTickets() {
        List<Ticket> allTickets = ticketService.findAllTickets();

        return ResponseEntity.ok(allTickets);
    }

    @GetMapping("/ticket/{id}")
    public ResponseEntity<Ticket> findTicketById(@PathVariable Integer id) {
        Ticket ticketById = ticketService.findTicketById(id);

        return ResponseEntity.ok(ticketById);
    }

    @PostMapping("/ticket")
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Ticket createdTicket = ticketService.createTicket(ticket);

        return ResponseEntity.ok(createdTicket);
    }

    @PutMapping("/ticket/{id}")
    public ResponseEntity<Ticket> updateTicket(@RequestBody Ticket ticket, @PathVariable Integer id) {
        Ticket updatedTicket = ticketService.updateTicket(ticket, id);

        return ResponseEntity.ok(updatedTicket);
    }

    @PatchMapping("/ticket/{id}")
    public ResponseEntity<Ticket> partiallyUpdateTicket(@RequestBody Ticket ticket, @PathVariable Integer id) {
        Ticket partiallyUpdatedTicket = ticketService.updateTicket(ticket, id);

        return ResponseEntity.ok(partiallyUpdatedTicket);
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<Void> deleteTicketById(@PathVariable Integer id) {
        ticketService.deleteTicketById(id);

        return ResponseEntity.noContent().build();
    }
}
