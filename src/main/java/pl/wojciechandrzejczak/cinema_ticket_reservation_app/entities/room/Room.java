package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import jakarta.persistence.*;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer seatsNumber;

    private String name;
    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    private Seance seance;

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

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", seatsNumber=" + seatsNumber +
                ", name='" + name + '\'' +
                ", seance=" + seance +
                '}';
    }
}
