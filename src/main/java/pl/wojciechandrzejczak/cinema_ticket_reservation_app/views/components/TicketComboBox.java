package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.provider.DataProvider;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;

public class TicketComboBox {
    private final TicketService ticketService;

    @Autowired
    public TicketComboBox(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    private ComboBox<Ticket> ticketComboBox() {
        ComboBox<Ticket> ticketComboBox = new ComboBox<>("Ticket");
        ticketComboBox.setSizeFull();
        ticketComboBox.setItems(DataProvider.ofCollection(ticketService.findAllTickets()));
        ticketComboBox.setItemLabelGenerator(ticket -> (ticket.getType() + " - Price - " + ticket.getPrice()));

        return ticketComboBox;
    }
}
