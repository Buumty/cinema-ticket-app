package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.RoomService;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SeanceService {
    private final SeanceRepository seanceRepository;
    private final RoomService roomService;

    @Autowired
    public SeanceService(SeanceRepository seanceRepository, RoomService roomService) {
        this.seanceRepository = seanceRepository;
        this.roomService = roomService;
    }

    public List<Seance> findAllSeances() {
        return seanceRepository.findAll();
    }

    public Seance findSeanceById(Integer id) {
        return seanceRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Seance createSeance(Seance seance) {
        seance.setTicketsAvailable(seance.getRoom().getSeatsNumber());
        seance.setEndTime(seance.getStartTime().plusMinutes(seance.getMovie().getLength()));
        return seanceRepository.save(seance);
    }

    public Seance updateSeance(Seance seance, Integer id) {
        Seance dbSeance = seanceRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (seance.getMovie() != null) {
            dbSeance.setMovie(seance.getMovie());
        }
        if (seance.getRoom() != null) {
            dbSeance.setRoom(seance.getRoom());
        }
        if (seance.getTicketsAvailable() != null) {
            dbSeance.setTicketsAvailable(seance.getTicketsAvailable());
        }
        if (seance.getStartTime() != null) {
            dbSeance.setStartTime(seance.getStartTime());
        }
        if (seance.getEndTime() != null) {
            dbSeance.setEndTime(seance.getEndTime());
        }

        return seanceRepository.save(dbSeance);
    }

    public void deleteSeanceById(Integer id) {
        if (seanceRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        seanceRepository.deleteById(id);
    }

    public SeanceDTO seanceDTOMapper(Seance seance) {
        SeanceDTO seanceDTO = new SeanceDTO();
        seanceDTO.setId(seance.getId());
        seanceDTO.setMovieName(seance.getMovie().getName());
        seanceDTO.setRoomName(seance.getRoom().getName());
        seanceDTO.setTicketsAvailable(seance.getTicketsAvailable());
        seanceDTO.setStartTime(seance.getStartTime());
        seanceDTO.setEndTime(seance.getEndTime());

        return seanceDTO;
    }
}
