package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.provider.DataProvider;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;

import java.util.List;

public class SeanceComboBox extends ComboBox<Seance> {
    private final SeanceService seanceService;
    private final ReservedSeatRepository reservedSeatRepository;
    private final List<SeatButton> selectedSeats;

    public SeanceComboBox(SeanceService seanceService, ReservedSeatRepository reservedSeatRepository, List<SeatButton> selectedSeats) {
        this.seanceService = seanceService;
        this.reservedSeatRepository = reservedSeatRepository;
        this.selectedSeats = selectedSeats;

        setLabel("Pick a seance");
        setItems(DataProvider.ofCollection(seanceService.findAllSeances()));
        setItemLabelGenerator(seance -> seance.getMovie().getName() + " - Hall: " + seance.getRoom().getName());

        setWidth("25%");
    }
}
