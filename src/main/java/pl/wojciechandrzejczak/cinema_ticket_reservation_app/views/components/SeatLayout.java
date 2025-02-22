package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

import java.util.List;

public class SeatLayout extends VerticalLayout {
    private final Seance seance;
    private final ReservedSeatRepository reservedSeatRepository;
    private final List<SeatButton> selectedSeats;

    public SeatLayout(Seance seance, ReservedSeatRepository reservedSeatRepository, List<SeatButton> selectedSeats) {
        this.seance = seance;
        this.reservedSeatRepository = reservedSeatRepository;
        this.selectedSeats = selectedSeats;
        removeAll();

        for (int row = 0; row < seance.getRoom().getRows(); row++) {
            HorizontalLayout rowLayout = new HorizontalLayout();
            for (int seat = 0; seat < seance.getRoom().getColumns(); seat++) {
                SeatButton seatButton = new SeatButton(reservedSeatRepository, seance, row, seat, selectedSeats);
                rowLayout.add(seatButton);
            }
            add(rowLayout);
        }
    }
}
