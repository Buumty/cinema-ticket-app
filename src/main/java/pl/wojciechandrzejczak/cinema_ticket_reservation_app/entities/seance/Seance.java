package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance;

import jakarta.persistence.*;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.Movie;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.Room;

import java.time.LocalDateTime;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "movie")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
    private Integer ticketsAvailable;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public Seance(){}

    public Seance(Movie movie, Room room, Integer ticketsAvailable, LocalDateTime startTime, LocalDateTime endTime) {
        this.movie = movie;
        this.room = room;
        this.ticketsAvailable = ticketsAvailable;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Integer getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(Integer ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Seance{" +
                "id=" + id +
                ", movie=" + movie +
                ", room=" + room +
                ", ticketsAvailable=" + ticketsAvailable +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
