package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, java.lang.Integer> {}
