package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datetimepicker.DateTimePicker;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.Movie;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.MovieService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.Room;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.RoomService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminHeader;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@UIScope
@Route("seance-list")
public class SeanceView extends VerticalLayout {
    private final SeanceService seanceService;
    private final MovieService movieService;
    private final RoomService roomService;
    private Grid<Seance> seanceGrid;
    private ComboBox<Movie> movieComboBox;
    private ComboBox<Room> roomComboBox;
    private DateTimePicker startTimePicker;

    @Autowired
    public SeanceView(SeanceService seanceService, MovieService movieService, RoomService roomService) {
        this.seanceService = seanceService;
        this.movieService = movieService;
        this.roomService = roomService;
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
        horizontalLayout.add(setUpSeanceGrid());
        horizontalLayout.add(setUpSeanceDataEntry());

        add(horizontalLayout);
    }

    private Grid<Seance> setUpSeanceGrid() {
        seanceGrid = new Grid<>(Seance.class, false);
        seanceGrid.setWidth("70%");
        seanceGrid.setHeightFull();

        seanceGrid.addColumn(seance -> (seance.getMovie().getName())).setHeader("Movie");
        seanceGrid.addColumn(seance -> (seance.getRoom().getName())).setHeader("Room");
        seanceGrid.addColumn(Seance::getStartTime).setHeader("Start Time");
        seanceGrid.addColumn(Seance::getEndTime).setHeader("End Time");
        seanceGrid.addColumn(Seance::getTicketsAvailable).setHeader("Tickets Available");

        seanceGrid.setItems(DataProvider.ofCollection(seanceService.findAllSeances()));

        return seanceGrid;
    }

    private VerticalLayout setUpSeanceDataEntry() {
        VerticalLayout seanceDataEntryLayout = new VerticalLayout();
        seanceDataEntryLayout.setWidth("30%");
        seanceDataEntryLayout.setHeightFull();

        seanceDataEntryLayout.add(movieComboBox());
        seanceDataEntryLayout.add(roomComboBox());
        seanceDataEntryLayout.add(startTimePicker());
        seanceDataEntryLayout.add(setUpSaveSeanceButton());
        seanceDataEntryLayout.add(setUpDeleteSeanceButton());

        return seanceDataEntryLayout;
    }



    private Button setUpSaveSeanceButton() {
        Button saveSeance = new Button("Save seance");
        saveSeance.addClickListener(buttonClickEvent -> {
            Movie movie = movieComboBox.getValue();
            Room room = roomComboBox.getValue();
            LocalDateTime startTime = startTimePicker.getValue();
            seanceService.createSeance(new Seance(movie,room,room.getSeatsNumber(),startTime));
            seanceGrid.setItems(seanceService.findAllSeances());
        });

        return saveSeance;
    }
    private Button setUpDeleteSeanceButton() {
        Button deleteSeance = new Button("Delete seance");
        deleteSeance.addClickListener(buttonClickEvent -> {
            Seance selectedSeance = seanceGrid.asSingleSelect().getValue();
            if (selectedSeance != null) {
                seanceService.deleteSeanceById(selectedSeance.getId());
                seanceGrid.setItems(seanceService.findAllSeances());
            } else {
                Notification.show("No seance selected!");
            }
        });

        return deleteSeance;
    }

    private DateTimePicker startTimePicker() {
        startTimePicker = new DateTimePicker("Start of seance");
        startTimePicker.setStep(Duration.ofMinutes(15));

        return startTimePicker;
    }

    private ComboBox<Room> roomComboBox() {
        roomComboBox = new ComboBox<>("Room");
        List<Room> allRooms = roomService.findAllRooms();

        roomComboBox.setItems(DataProvider.ofCollection(allRooms));
        roomComboBox.setItemLabelGenerator(Room::getName);

        return roomComboBox;
    }

    private ComboBox<Movie> movieComboBox() {
        movieComboBox = new ComboBox<>("Movie");
        List<Movie> allMovies = movieService.findAllMovies();

        movieComboBox.setItems(DataProvider.ofCollection(allMovies));
        movieComboBox.setItemLabelGenerator(Movie::getName);

        return movieComboBox;
    }

}
