package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.MovieRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.RoomRepository;

import java.net.URI;
import java.util.List;

@Controller
public class SeanceController {
    private final SeanceService seanceService;

    @Autowired
    public SeanceController(SeanceService seanceService) {
        this.seanceService = seanceService;
    }

    @GetMapping("/seance")
    public ResponseEntity<List<Seance>> findAllSeances() {
        List<Seance> allSeances = seanceService.findAllSeances();

        return ResponseEntity.ok(allSeances);
    }
    @GetMapping("/seance/{id}")
    public ResponseEntity<Seance> findSeanceById(@PathVariable Integer id) {
        Seance seanceById = seanceService.findSeanceById(id);

        return ResponseEntity.ok(seanceById);
    }

    @PostMapping("/seance")
    public ResponseEntity<SeanceDTO> createSeance(@RequestBody Seance seance) {
        Seance createdSeance = seanceService.createSeance(seance);

        return ResponseEntity.created(URI.create("/seance/"+ createdSeance.getId())).body(seanceService.seanceDTOMapper(createdSeance));
    }

    @PutMapping("/seance/{id}")
    public ResponseEntity<Seance> updateSeance(@RequestBody Seance seance, @PathVariable Integer id) {
        Seance updatedSeance = seanceService.updateSeance(seance, id);

        return ResponseEntity.ok(updatedSeance);
    }

    @PatchMapping("/seance/{id}")
    public ResponseEntity<Seance> partiallyUpdatedSeance(@RequestBody Seance seance, @PathVariable Integer id) {
        Seance partiallyUpdatedSeance = seanceService.updateSeance(seance, id);

        return ResponseEntity.ok(partiallyUpdatedSeance);
    }

    @DeleteMapping("/seance/{id}")
    public ResponseEntity<Void> deleteSeanceById(@PathVariable Integer id) {
        seanceService.deleteSeanceById(id);

        return ResponseEntity.noContent().build();
    }
}
