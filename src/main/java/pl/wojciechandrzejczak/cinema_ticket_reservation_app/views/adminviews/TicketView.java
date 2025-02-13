package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminHeader;

@UIScope
@Route("ticket-list")
public class TicketView extends VerticalLayout {
    private final TicketService ticketService;
    private Grid<Ticket> ticketGrid;


    @Autowired
    public TicketView(TicketService ticketService) {
        this.ticketService = ticketService;
        setPadding(true);
        setSpacing(true);
        setSizeFull();
        add(new AdminHeader());
        mainBody();
        add(new AdminFooter());
    }

    private void mainBody() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.add(setUpTicketGrid());
        horizontalLayout.add(setUpTicketDataEntry());

        add(horizontalLayout);
    }

    private Grid<Ticket> setUpTicketGrid() {
        ticketGrid = new Grid<>(Ticket.class, false);
        ticketGrid.setWidth("70%");
        ticketGrid.setHeightFull();

        ticketGrid.addColumn(Ticket::getType).setHeader("Type");
        ticketGrid.addColumn(Ticket::getPrice).setHeader("Price");

        ticketGrid.setItems(DataProvider.ofCollection(ticketService.findAllTickets()));

        return ticketGrid;
    }

    private VerticalLayout setUpTicketDataEntry() {
        VerticalLayout ticketDataEntryLayout = new VerticalLayout();
        ticketDataEntryLayout.setWidth("30%");
        ticketDataEntryLayout.setHeightFull();

        TextField type = new TextField("Type");
        NumberField price = new NumberField("Price");

        ticketDataEntryLayout.add(type);
        ticketDataEntryLayout.add(price);
        ticketDataEntryLayout.add(setUpSaveTicketButton(type, price));
        ticketDataEntryLayout.add(setUpDeleteTicketButton());

        return ticketDataEntryLayout;
    }

    private Button setUpSaveTicketButton(TextField type, NumberField price) {
        Button saveTicket = new Button("Save ticket");

        saveTicket.addClickListener(buttonClickEvent -> {
            ticketService.createTicket(new Ticket(type.getValue(), price.getValue()));
            ticketGrid.setItems(ticketService.findAllTickets());
        });

        return saveTicket;
    }
    private Button setUpDeleteTicketButton() {
        Button deleteTicket = new Button("Delete ticket");

        deleteTicket.addClickListener(buttonClickEvent -> {
            Ticket selectedTicket = ticketGrid.asSingleSelect().getValue();
            if (selectedTicket != null) {
                ticketService.deleteTicketById(selectedTicket.getId());
                ticketGrid.setItems(ticketService.findAllTickets());
            } else {
                Notification.show("No ticket selected!");
            }
        });

        return deleteTicket;
    }
}
