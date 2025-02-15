package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeat;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserHeader;

import java.util.List;

@UIScope
@Route("hall")
public class HallView extends VerticalLayout {
    private final SeanceService seanceService;
    private final ReservedSeatRepository reservedSeatRepository;
    private final VerticalLayout seatLayout = new VerticalLayout();
    private ComboBox<Seance> seanceComboBox;

    @Autowired
    public HallView(SeanceService seanceService, ReservedSeatRepository reservedSeatRepository) {
        this.seanceService = seanceService;
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

        horizontalLayout.add(seanceComboBox, seatLayout);
        add(horizontalLayout);
    }

    private void createSeatLayout(Seance seance) {
        seatLayout.removeAll();
        List<ReservedSeat> reservedSeats = reservedSeatRepository.findBySeance(seance);

        for (int row = 0; row < seance.getRoom().getRows(); row++) {
            final int rowFinal = row;
            HorizontalLayout rowLayout = new HorizontalLayout();
            for (int seat = 0; seat < seance.getRoom().getColumns(); seat++) {
                final int seatFinal = seat;
                Button seatButton = new Button((row + 1) + "-" + (seat + 1));

                boolean isReserved = reservedSeats.stream()
                        .anyMatch(reservedSeat -> reservedSeat.getRowNumber() == rowFinal && reservedSeat.getSeatNumber() == seatFinal);

                if (isReserved) {
                    seatButton.setEnabled(false);
                } else {
                    seatButton.addClickListener(e -> reserveSeat(seance, rowFinal, seatFinal, seatButton));
                }

                rowLayout.add(seatButton);
            }
            seatLayout.add(rowLayout);
        }
        seatLayout.setWidth("100%");
        seatLayout.setHeightFull();
    }

    private void reserveSeat(Seance seance, int row, int seat, Button seatButton) {
        ReservedSeat reservedSeat = new ReservedSeat();
        reservedSeat.setSeance(seance);
        reservedSeat.setRowNumber(row);
        reservedSeat.setSeatNumber(seat);
        reservedSeatRepository.save(reservedSeat);

        seatButton.setEnabled(false);
        Notification.show("Zarezerwowano miejsce: " + seatButton.getText());
    }
}