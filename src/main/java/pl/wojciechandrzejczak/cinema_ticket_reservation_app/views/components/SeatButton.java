package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.button.Button;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeat;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

import java.util.List;

public class SeatButton extends Button {
    public static final String DISABLED_BACKGROUND_COLOR = "#808080";
    public static final String UNCHECKED_BACKGROUND_COLOR = "#00FF00";
    public static final String CHECKED_BACKGROUND_COLOR = "#FFFF00";
    private final ReservedSeatRepository reservedSeatRepository;
    private final Seance seance;
    private final int row;
    private final int seat;
    public SeatButton(ReservedSeatRepository reservedSeatRepository, Seance seance, int row, int seat, List<SeatButton> selectedSeats){
        this.reservedSeatRepository = reservedSeatRepository;
        this.seance = seance;
        this.row = row;
        this.seat = seat;
        setText((row + 1) + "-" + (seat + 1));
        setStyle();
        addClickListener(event -> {
            if (getStyle().get("background-color").equals(UNCHECKED_BACKGROUND_COLOR)) {
                selectedSeats.add(this);
                getStyle().set("background-color", CHECKED_BACKGROUND_COLOR);
            } else if (getStyle().get("background-color").equals(CHECKED_BACKGROUND_COLOR)) {
                selectedSeats.remove(this);
                getStyle().set("background-color", UNCHECKED_BACKGROUND_COLOR);
            }
        });
    }
    private void setStyle() {
        getStyle().set("background-color", UNCHECKED_BACKGROUND_COLOR);
        if (isReserved()) {
            getStyle().set("background-color", DISABLED_BACKGROUND_COLOR);
            setEnabled(false);
        }
    }

    private boolean isReserved() {
        List<ReservedSeat> reservedSeats = reservedSeatRepository.findBySeance(seance);

        return reservedSeats.stream()
                .anyMatch(reservedSeat -> reservedSeat.getRowNumber() == row && reservedSeat.getSeatNumber() == seat);
    }
}