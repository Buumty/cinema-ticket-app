package pl.wojciechandrzejczak.cinema_ticket_reservation_app;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.Movie;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.MovieRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.Room;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.RoomRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.SeanceRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketRepository;

import java.time.LocalDateTime;

@Component
public class DataInitializer implements CommandLineRunner {
    private final MovieRepository movieRepository;
    private final TicketRepository ticketRepository;
    private final RoomRepository roomRepository;
    private final SeanceRepository seanceRepository;

    public DataInitializer(MovieRepository movieRepository, TicketRepository ticketRepository, RoomRepository roomRepository, SeanceRepository seanceRepository) {
        this.movieRepository = movieRepository;
        this.ticketRepository = ticketRepository;
        this.roomRepository = roomRepository;
        this.seanceRepository = seanceRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (movieRepository.count() == 0 && ticketRepository.count() == 0 && roomRepository.count() == 0 && seanceRepository.count() == 0) {

            Movie actionMovie = new Movie("Movie-1", 120, "Action");
            Movie adventureMovie = new Movie("Movie-2", 130, "Adventure");
            Movie fantasyMovie = new Movie("Movie-3", 125, "Fantasy");

            movieRepository.save(actionMovie);
            movieRepository.save(adventureMovie);
            movieRepository.save(fantasyMovie);

            Ticket normal = new Ticket("Normal", 10.00);
            Ticket child = new Ticket("Child", 5.00);
            Ticket senior = new Ticket("Senior", 5.00);

            ticketRepository.save(normal);
            ticketRepository.save(child);
            ticketRepository.save(senior);

            Room room1 = new Room(10, 5, "Room-1");
            Room room2 = new Room(8, 6, "Room-2");
            Room room3 = new Room(9, 7, "Room-3");

            roomRepository.save(room1);
            roomRepository.save(room2);
            roomRepository.save(room3);

            Seance seance1 = new Seance(actionMovie,room1, room1.getSeatsNumber(), LocalDateTime.of(2025, 7, 22, 12, 30));
            Seance seance2 = new Seance(fantasyMovie, room2, room2.getSeatsNumber(), LocalDateTime.of(2025, 7, 22, 13, 30));
            Seance seance3 = new Seance(adventureMovie, room3, room3.getSeatsNumber(), LocalDateTime.of(2025, 7, 22, 14, 30));

            seanceRepository.save(seance1);
            seanceRepository.save(seance2);
            seanceRepository.save(seance3);
        }
    }
}
