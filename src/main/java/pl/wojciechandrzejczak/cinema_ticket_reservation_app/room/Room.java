package pl.wojciechandrzejczak.cinema_ticket_reservation_app.room;

import jakarta.persistence.*;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.movie.Movie;

import java.util.List;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seatsNumber;
    private Integer freeSeatsNumber;
    private Integer takenSeatsNumber;
    public Room(){}

    public Room(Integer seatsNumber, Integer freeSeatsNumber, Integer takenSeatsNumber) {
        this.seatsNumber = seatsNumber;
        this.freeSeatsNumber = freeSeatsNumber;
        this.takenSeatsNumber = takenSeatsNumber;
    }

    public Integer getId() {
        return id;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Integer getFreeSeatsNumber() {
        return freeSeatsNumber;
    }

    public void setFreeSeatsNumber(Integer freeSeatsNumber) {
        this.freeSeatsNumber = freeSeatsNumber;
    }

    public Integer getTakenSeatsNumber() {
        return takenSeatsNumber;
    }

    public void setTakenSeatsNumber(Integer takenSeatsNumber) {
        this.takenSeatsNumber = takenSeatsNumber;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", seatingsNumber=" + seatsNumber +
                ", freeSeatingsNumber=" + freeSeatsNumber +
                ", takenSeatingsNumber=" + takenSeatsNumber +
                '}';
    }
}
