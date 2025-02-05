package pl.wojciechandrzejczak.cinema_ticket_reservation_app.seance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeanceRepository extends JpaRepository<Seance, Integer> {
}
