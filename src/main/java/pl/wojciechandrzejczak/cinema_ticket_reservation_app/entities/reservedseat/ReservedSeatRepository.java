package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

import java.util.List;

public interface ReservedSeatRepository extends JpaRepository<ReservedSeat, Long> {
    List<ReservedSeat> findBySeance(Seance seance);
}
