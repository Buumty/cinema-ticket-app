package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import jakarta.persistence.*;

@Entity
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer rows;
    private Integer columns;
    private String name;
    private Integer seatsNumber;

    public Room(){}

    public Room(Integer rows, Integer columns, String name) {
        this.rows = rows;
        this.columns = columns;
        this.name = name;
    }

    @PrePersist
    @PreUpdate
    public void calculateTicketsAvailable() {
        if (getRows() != null && getColumns() != null) {
            seatsNumber = getColumns() * getRows();
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(Integer seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", rows=" + rows +
                ", columns=" + columns +
                ", name='" + name + '\'' +
                ", seatsNumber=" + seatsNumber +
                '}';
    }
}
