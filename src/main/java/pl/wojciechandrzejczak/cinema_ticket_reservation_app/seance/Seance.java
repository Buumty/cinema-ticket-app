package pl.wojciechandrzejczak.cinema_ticket_reservation_app.seance;

import jakarta.persistence.*;

@Entity
public class Seance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Integer movieId;
    @OneToOne
    private Integer roomId;
    private Integer ticketsAvailable;
    private String startTime;
    private String endTime;
}
