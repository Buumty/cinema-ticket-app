package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, java.lang.Integer> {}
