package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views;

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
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.Movie;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.MovieService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminHeader;

@UIScope
@Route("movie-list")
public class MovieView extends VerticalLayout {
    private final MovieService movieService;
    private Grid<Movie> movieGrid;

    @Autowired
    public MovieView(MovieService movieService) {
        this.movieService = movieService;
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
        horizontalLayout.add(setUpMovieGrid());
        horizontalLayout.add(setUpMovieDataEntry());

        add(horizontalLayout);
    }

    private Grid<Movie> setUpMovieGrid() {
        movieGrid = new Grid<>(Movie.class, false);
        movieGrid.setWidth("70%");
        movieGrid.setHeightFull();

        movieGrid.addColumn(Movie::getName).setHeader("Name");
        movieGrid.addColumn(Movie::getDescription).setHeader("Description");
        movieGrid.addColumn(Movie::getLength).setHeader("Length");

        movieGrid.setItems(DataProvider.ofCollection(movieService.findAllMovies()));

        return movieGrid;
    }
    private VerticalLayout setUpMovieDataEntry() {
        VerticalLayout movieDataEntryLayout = new VerticalLayout();
        movieDataEntryLayout.setWidth("30%");
        movieDataEntryLayout.setHeightFull();

        TextField name = new TextField("Name");
        TextField description = new TextField("Description");
        NumberField length = new NumberField("Length");

        movieDataEntryLayout.add(name);
        movieDataEntryLayout.add(description);
        movieDataEntryLayout.add(length);
        movieDataEntryLayout.add(setUpSaveMovieButton(name, description, length));
        movieDataEntryLayout.add(setUpDeleteMovieButton());

        return movieDataEntryLayout;
    }
    private Button setUpSaveMovieButton(TextField name, TextField description, NumberField length) {
        Button saveMovie = new Button("Save movie");

        saveMovie.addClickListener(buttonClickEvent -> {
            movieService.createMovie(new Movie(name.getValue(),length.getValue().intValue(),description.getValue()));
            movieGrid.setItems(movieService.findAllMovies());
        });

        return saveMovie;
    }
    private Button setUpDeleteMovieButton() {
        Button deleteMovie = new Button("Delete movie");

        deleteMovie.addClickListener(buttonClickEvent -> {
            Movie selectedMovie = movieGrid.asSingleSelect().getValue();
            if (selectedMovie != null) {
                movieService.deleteMovieById(selectedMovie.getId());
                movieGrid.setItems(movieService.findAllMovies());
            } else {
                Notification.show("No movie selected!");
            }
        });

        return deleteMovie;
    }

}
