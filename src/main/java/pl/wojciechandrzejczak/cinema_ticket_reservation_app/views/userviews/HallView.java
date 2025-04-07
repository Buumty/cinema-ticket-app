package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.ReservationService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@UIScope
@Route("hall")
public class HallView extends VerticalLayout {
    private final SeanceService seanceService;
    private final TicketService ticketService;
    private final ReservationService reservationService;


    private final ReservedSeatRepository reservedSeatRepository;
    private final VerticalLayout seatLayout = new VerticalLayout();
    private Button reserveTicketsButton;
    private Button selectTicketsButton;
    private SeanceComboBox seanceComboBox;
    private final List<SeatButton> selectedSeats = new ArrayList<>();
    private final Map<SeatButton, Ticket> selectedTickets = new HashMap<>();
    private final List<ComboBox<Ticket>> ticketComboBoxes = new ArrayList<>();
    private Text totalPriceText;

    @Autowired
    public HallView(SeanceService seanceService, TicketService ticketService, ReservationService reservationService, ReservedSeatRepository reservedSeatRepository) {
        this.seanceService = seanceService;
        this.ticketService = ticketService;
        this.reservationService = reservationService;
        this.reservedSeatRepository = reservedSeatRepository;

        setJustifyContentMode(JustifyContentMode.CENTER);
        setAlignItems(Alignment.CENTER);
        setSizeFull();
        add(new UserHeader());
        add(mainBody());
        add(new UserFooter());
    }

    private HorizontalLayout mainBody() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("80%");
        horizontalLayout.setHeightFull();
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        selectTicketsButton = new Button("Select Tickets");

        seanceComboBox = new SeanceComboBox(seanceService, reservedSeatRepository, selectedSeats);
        seanceComboBox.addValueChangeListener(event -> {
            createSeatLayout(seanceComboBox.getValue());
        });
        horizontalLayout.add(seanceComboBox, seatLayout, rightPanel());

        return horizontalLayout;
    }

    private HorizontalLayout rightPanel() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.add(createTicketSelectionLayout(), createTicketsReservationLayout());
        return horizontalLayout;
    }

    private VerticalLayout createTicketSelectionLayout() {
        VerticalLayout ticketSelectionLayout = new VerticalLayout();
        ticketSelectionLayout.add(selectTicketsButton);

        selectTicketsButton.addClickListener(buttonClickEvent -> {
            totalPriceText.setText("Total Price: 0");
            ticketSelectionLayout.removeAll();
            ticketSelectionLayout.add(selectTicketsButton, ticketsComboBoxesLayout());
        });
        return ticketSelectionLayout;
    }

    private VerticalLayout createTicketsReservationLayout() {
        VerticalLayout ticketReservationLayout = new VerticalLayout();
        totalPriceText = new Text("Price: 0.0");
        reserveTicketsButton = new Button("Reserve Tickets");
        reserveTicketsButton.setEnabled(false);
        reserveTicketsButton.addClickListener(event -> {
            reservationService.reserveSelectedTickets(seanceComboBox.getValue(), selectedSeats, selectedTickets);

            Seance selectedSeance = seanceComboBox.getValue();
            if (selectedSeance != null) {
                for (int i = 0; i < selectedSeats.size(); i++) {
                    Anchor downloadLink = reservationService.createDownloadLink(ticketComboBoxes.get(i).getValue(), selectedSeance, selectedSeats.get(i).getText());
                    downloadLink.setText("Ticket " + selectedSeats.get(i).getText());
                    ticketReservationLayout.add(downloadLink);
                }
            }
            selectedSeats.clear();
            selectedTickets.clear();
            seatLayout.removeAll();
            createSeatLayout(seanceComboBox.getValue());
        });
        ticketReservationLayout.add(reserveTicketsButton, totalPriceText);
        return ticketReservationLayout;
    }

    private VerticalLayout ticketsComboBoxesLayout() {
        VerticalLayout ticketSelectionLayout = new VerticalLayout();
        ticketSelectionLayout.removeAll();
        ticketComboBoxes.clear();
        selectedTickets.clear();

        for (SeatButton selectedSeat : selectedSeats) {
            TicketComboBox ticketComboBox = new TicketComboBox(ticketService);

            ticketComboBox.addValueChangeListener(event -> {
                if (event.getValue() != null) {
                    selectedTickets.put(selectedSeat, event.getValue());
                } else {
                    selectedTickets.remove(selectedSeat);
                }
                reserveTicketsButton.setEnabled(areAllTicketsSelected());
                updateTotalPrice();
            });
            ticketComboBoxes.add(ticketComboBox);
            ticketSelectionLayout.add(ticketComboBox);
        }

        return ticketSelectionLayout;
    }

    private void updateTotalPrice() {
        double totalPriceDouble = selectedTickets.values().stream().mapToDouble(Ticket::getPrice).sum();
        totalPriceText.setText("Total Price: " + totalPriceDouble);
    }

    private boolean areAllTicketsSelected() {
        return ticketComboBoxes.stream().allMatch(comboBox -> comboBox.getValue() != null);
    }

    private void createSeatLayout(Seance seance) {
        seatLayout.removeAll();

        for (int row = 0; row < seance.getRoom().getRows(); row++) {
            HorizontalLayout rowLayout = new HorizontalLayout();
            for (int seat = 0; seat < seance.getRoom().getColumns(); seat++) {
                SeatButton seatButton = new SeatButton(reservedSeatRepository, seanceComboBox.getValue(), row, seat, selectedSeats);
                rowLayout.add(seatButton);
            }
            seatLayout.add(rowLayout);
        }
    }
}