package pl.wojciechandrzejczak.cinema_ticket_reservation_app.room;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seatsNumber;

    private String name;

    public Room(){}

    public Room(Integer seatsNumber, String name) {
        this.seatsNumber = seatsNumber;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", seatsNumber=" + seatsNumber +
                ", name='" + name + '\'' +
                '}';
    }
}
