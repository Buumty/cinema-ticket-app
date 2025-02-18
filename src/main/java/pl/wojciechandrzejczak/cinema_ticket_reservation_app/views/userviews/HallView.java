package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeat;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.SeatButton;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserHeader;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@UIScope
@Route("hall")
public class HallView extends VerticalLayout {
    private final SeanceService seanceService;
    private final TicketService ticketService;
    private final ReservedSeatRepository reservedSeatRepository;
    private final VerticalLayout seatLayout = new VerticalLayout();
    private ComboBox<Seance> seanceComboBox;
    private Button reserveTicketsButton;
    private Button selectTicketsButton;
    private BigDecimal finalPrice;
    private final List<SeatButton> selectedSeats = new ArrayList<>();
    private final List<Ticket> selectedTickets = new ArrayList<>();

    @Autowired
    public HallView(SeanceService seanceService, TicketService ticketService, ReservedSeatRepository reservedSeatRepository) {
        this.seanceService = seanceService;
        this.ticketService = ticketService;
        this.reservedSeatRepository = reservedSeatRepository;
        setJustifyContentMode(JustifyContentMode.CENTER);
        setSizeFull();
        add(new UserHeader());
        mainBody();
        add(new UserFooter());
    }

    private void mainBody() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setHeight("80%");
        horizontalLayout.setWidth("80%");
        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);

        List<Seance> allSeances = seanceService.findAllSeances();
        seanceComboBox = new ComboBox<>("Pick a seance", allSeances);
        seanceComboBox.setItemLabelGenerator(seance ->
                seance.getMovie().getName() + " - Hall: " + seance.getRoom().getName());

        seanceComboBox.addValueChangeListener(event -> {
            seatLayout.removeAll();
            if (event.getValue() != null) {
                createSeatLayout(event.getValue());
            }
        });
        seanceComboBox.setWidth("25%");

        selectTicketsButton = new Button("Select Tickets");



        horizontalLayout.add(seanceComboBox, seatLayout, rightPanel());
        add(horizontalLayout);
    }

    private HorizontalLayout rightPanel() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout verticalLayout1 = new VerticalLayout();
        finalPrice = BigDecimal.valueOf(0);
        verticalLayout1.add(selectTicketsButton);
        selectTicketsButton.addClickListener(buttonClickEvent -> {
            verticalLayout1.add(ticketsComboBoxesLayout());
        });

        VerticalLayout verticalLayout2 = new VerticalLayout();
        reserveTicketsButton = new Button("Reserve Tickets");
        reserveTicketsButton.setEnabled(false);
        reserveTicketsButton.addClickListener(event -> {
            for (SeatButton seatButton : selectedSeats) {
                String[] seatPosition = seatButton.getText().split("-");
                int row = Integer.parseInt(seatPosition[0]) - 1;
                int seat = Integer.parseInt(seatPosition[1]) - 1;
                reserveTickets(seanceComboBox.getValue(), row, seat);
                System.out.println("Saving ");
            }
            int i = 0;
            for (Ticket selectedTicket : selectedTickets) {
                try {
                    System.out.println("GenerateTicekt");
                    ticketService.generatePdfTicket(selectedTicket, seanceComboBox.getValue(), selectedSeats.get(i).getText());
                    i++;
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            selectedSeats.clear();
            seatLayout.removeAll();
            createSeatLayout(seanceComboBox.getValue());
        });
        Text text = new Text(finalPrice.toString());

        verticalLayout2.add(reserveTicketsButton, text);

        horizontalLayout.add(verticalLayout1, reserveTicketsButton);
        return horizontalLayout;
    }

    private void createSeatLayout(Seance seance) {
        seatLayout.removeAll();
        List<ReservedSeat> reservedSeats = reservedSeatRepository.findBySeance(seance);

        for (int row = 0; row < seance.getRoom().getRows(); row++) {
            final int rowFinal = row;
            HorizontalLayout rowLayout = new HorizontalLayout();
            for (int seat = 0; seat < seance.getRoom().getColumns(); seat++) {
                final int seatFinal = seat;
                SeatButton seatButton = new SeatButton();
                seatButton.setText((row + 1) + "-" + (seat + 1));
                seatButton.setValue(true);
                boolean isReserved = reservedSeats.stream()
                        .anyMatch(reservedSeat -> reservedSeat.getRowNumber() == rowFinal && reservedSeat.getSeatNumber() == seatFinal);
                if (isReserved) {
                    seatButton.setEnabled(false);
                    seatButton.getStyle().set("background-color", SeatButton.DISABLED_BACKGROUND_COLOR);
                }

                seatButton.addClickListener(buttonClickEvent -> {
                    seatButton.changeValue();
                    if (seatButton.isValue()) {
                        selectedSeats.remove(seatButton);
                    } else {
                        selectedSeats.add(seatButton);
                    }
                    reserveTicketsButton.setEnabled(!selectedSeats.isEmpty());

                });

                rowLayout.add(seatButton);
            }
            seatLayout.add(rowLayout);
        }
        seatLayout.setWidth("100%");
        seatLayout.setHeightFull();
    }
    private void reserveTickets(Seance seance, int row, int seat) {
        ReservedSeat reservedSeat = new ReservedSeat();
        reservedSeat.setSeance(seance);
        reservedSeat.setRowNumber(row);
        reservedSeat.setSeatNumber(seat);

        reservedSeatRepository.save(reservedSeat);
    }

    private VerticalLayout ticketsComboBoxesLayout() {
        VerticalLayout horizontalLayout = new VerticalLayout();
        for (SeatButton selectedSeat : selectedSeats) {
            ComboBox<Ticket> ticketComboBox = new ComboBox<>();
            ticketComboBox.setItems(ticketService.findAllTickets());
            ticketComboBox.setItemLabelGenerator(ticket -> (ticket.getType() + " - Price - " + ticket.getPrice()));
            ticketComboBox.addValueChangeListener(event -> {
                selectedTickets.add(ticketComboBox.getValue());
            });
            horizontalLayout.add(ticketComboBox);
        }

        return horizontalLayout;
    }
}