package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeat;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketDetails;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.SeatButton;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserHeader;

import java.io.ByteArrayInputStream;
import java.util.*;

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
    private final List<SeatButton> selectedSeats = new ArrayList<>();
    private final Map<SeatButton, Ticket> selectedTickets = new HashMap<>();
    private final List<ComboBox<Ticket>> ticketComboBoxes = new ArrayList<>();
    private Text totalPriceText;

    @Autowired
    public HallView(SeanceService seanceService, TicketService ticketService, ReservedSeatRepository reservedSeatRepository) {
        this.seanceService = seanceService;
        this.ticketService = ticketService;
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
        reserveTicketsButton = new Button("Reserve Tickets");
        reserveTicketsButton.setEnabled(false);

        setSeanceComboBox();
        horizontalLayout.add(seanceComboBox, seatLayout, rightPanel());

        return horizontalLayout;
    }



    private HorizontalLayout rightPanel() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        VerticalLayout ticketSelectionLayout = new VerticalLayout();
        VerticalLayout ticketReservationLayout = new VerticalLayout();
        totalPriceText = new Text("Total Price:  0");
        ticketSelectionLayout.add(selectTicketsButton);
        ticketReservationLayout.add(reserveTicketsButton, totalPriceText);

        selectTicketsButton.addClickListener(buttonClickEvent -> {
            totalPriceText.setText("Total Price: 0");
            ticketSelectionLayout.removeAll();
            ticketSelectionLayout.add(selectTicketsButton, ticketsComboBoxesLayout());
        });

        reserveTicketsButton.addClickListener(event -> {
            reserveSelectedTickets();
            Seance selectedSeance = seanceComboBox.getValue();
            if (selectedSeance != null) {
                for (int i = 0; i < selectedSeats.size(); i++) {
                    Anchor downloadLink = createDownloadLink(ticketComboBoxes.get(i).getValue(), selectedSeance, selectedSeats.get(i).getText());
                    ticketSelectionLayout.add(downloadLink);
                }
            }
        });



        horizontalLayout.add(ticketSelectionLayout, ticketReservationLayout);
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
                SeatButton seatButton = new SeatButton((row + 1) + "-" + (seat + 1));
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
                });
                rowLayout.add(seatButton);
            }
            seatLayout.add(rowLayout);
        }
    }

    private void reserveSelectedTickets() {
        if (seanceComboBox.getValue() == null) return;

        for (SeatButton seatButton : selectedSeats) {
            String[] seatPosition = seatButton.getText().split("-");
            int row = Integer.parseInt(seatPosition[0]) - 1;
            int seat = Integer.parseInt(seatPosition[1]) - 1;
            reserveTickets(seanceComboBox.getValue(), row, seat);
        }

        for (int i = 0; i < selectedSeats.size(); i++) {
            List<Ticket> selectedTicketList = selectedTickets.values().stream().toList();
            try {
                System.out.println("GenerateTicekt");

                ticketService.generatePdfTicket(selectedTicketList.get(i), seanceComboBox.getValue(), selectedSeats.get(i).getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        selectedSeats.clear();
        selectedTickets.clear();
        seatLayout.removeAll();
        createSeatLayout(seanceComboBox.getValue());

    }


    private void reserveTickets(Seance seance, int row, int seat) {
        ReservedSeat reservedSeat = new ReservedSeat(seance, row, seat);
        reservedSeatRepository.save(reservedSeat);
    }

    private void setSeanceComboBox() {
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
    }
    private VerticalLayout ticketsComboBoxesLayout() {
        VerticalLayout ticketSelectionLayout = new VerticalLayout();
        ticketSelectionLayout.removeAll();
        ticketComboBoxes.clear();
        selectedTickets.clear();

        for (SeatButton selectedSeat : selectedSeats) {
            ComboBox<Ticket> ticketComboBox = new ComboBox<>();
            ticketComboBox.setItems(ticketService.findAllTickets());
            ticketComboBox.setItemLabelGenerator(ticket -> (ticket.getType() + " - Price - " + ticket.getPrice()));
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

    private Anchor createDownloadLink(Ticket ticket, Seance seance, String seat) {
        try {
            byte[] pdfBytes = ticketService.generatePdfTicket(ticket, seance, seat);

            StreamResource resource = new StreamResource("ticket_" + ticket.getId() + ".pdf",
                    () -> new ByteArrayInputStream(pdfBytes));
            resource.setContentType("application/pdf");

            return new Anchor(resource, "Download Ticket");
        } catch (Exception e) {
            e.printStackTrace();
            return new Anchor("#", "Error generating ticket");
        }
    }
}