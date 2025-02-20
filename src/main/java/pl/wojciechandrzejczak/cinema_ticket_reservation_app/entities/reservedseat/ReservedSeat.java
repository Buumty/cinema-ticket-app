package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat;

import jakarta.persistence.*;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

@Entity
public class ReservedSeat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "seance_id")
    private Seance seance;

    private int rowNumber;
    private int seatNumber;

    public ReservedSeat() {}

    public ReservedSeat(Seance seance, int rowNumber, int seatNumber) {
        this.seance = seance;
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "ReservedSeat{" +
                "id=" + id +
                ", seance=" + seance +
                ", rowNumber=" + rowNumber +
                ", seatNumber=" + seatNumber +
                '}';
    }
}
