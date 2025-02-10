package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;

import java.util.List;

@UIScope
@Route("ticket-list")
public class TicketView extends VerticalLayout {
    private final TicketService ticketService;

    @Autowired
    public TicketView(TicketService ticketService) {
        this.ticketService = ticketService;
        setWidth("100%");
        setHeight("100%");
        setPadding(true);
        setMargin(true);
        setSpacing(true);
        setUpTicketGrid();
    }

    private void setUpTicketGrid() {
        Grid<Ticket> ticketGrid = new Grid<>(Ticket.class, false);
        ticketGrid.setWidth("100%");
        ticketGrid.setHeight("100%");

        ticketGrid.addColumn(Ticket::getType).setHeader("Type");
        ticketGrid.addColumn(Ticket::getPrice).setHeader("Price");

        List<Ticket> allTickets = ticketService.findAllTickets();
        System.out.println("Liczba biletów: " + allTickets.size());
        ticketGrid.setItems(allTickets);

        add(ticketGrid);
    }
}
