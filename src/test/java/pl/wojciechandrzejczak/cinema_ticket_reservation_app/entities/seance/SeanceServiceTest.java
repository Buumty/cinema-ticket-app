package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie.Movie;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.Room;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SeanceServiceTest {
    @Mock
    private SeanceRepository seanceRepository;
    private SeanceService seanceService;

    @BeforeEach
    public void setUp() {
        this.seanceService = new SeanceService(seanceRepository);
    }

    @Test
    public void givenNoSeances_whenFindAllSeances_thenReturnEmptyList() {
        when(seanceRepository.findAll()).thenReturn(emptyList());
        List<Seance> allSeances = seanceService.findAllSeances();

        assertNotNull(allSeances);
        assertEquals(0, allSeances.size(), "The list size should be 0 but is not!");
        verify(seanceRepository, times(1)).findAll();
    }

    @Test
    public void givenMultipleSeances_whenFindAllSeances_thenReturnCorrectList() {
        when(seanceRepository.findAll()).thenReturn(List.of(new Seance(), new Seance(), new Seance()));
        List<Seance> allSeances = seanceService.findAllSeances();

        assertNotNull(allSeances);
        assertEquals(3, allSeances.size(), "The list size should be 3 but is not!");
        verify(seanceRepository, times(1)).findAll();
    }

    @Test
    public void givenCorrectId_whenFindSeanceById_thenReturnCorrectEntity() {
        int correctId = 1;
        Seance seance = new Seance();
        seance.setRoom(new Room());
        seance.setMovie(new Movie());
        seance.setTicketsAvailable(100);
        seance.setId(correctId);
        seance.setStartTime(LocalDateTime.of(2023,8,12,14,30,45));
        seance.setEndTime(LocalDateTime.of(2024,11,6,8,15,23));


        when(seanceRepository.findById(correctId)).thenReturn(Optional.of(seance));
        Seance seanceById = seanceService.findSeanceById(correctId);

        assertNotNull(seanceById);
        assertNotNull(seanceById.getMovie());
        assertNotNull(seanceById.getRoom());
        assertEquals(1,seanceById.getId(),"The seance id should be 1 but is not!");
        assertEquals(100, seanceById.getTicketsAvailable(), "The seance tickets available should be 100 but is not!");
        assertEquals(LocalDateTime.of(2023,8,12,14,30,45), seanceById.getStartTime());
        assertEquals(LocalDateTime.of(2024,11,6,8,15,23), seanceById.getEndTime());
        verify(seanceRepository, times(1)).findById(correctId);
    }

    @Test
    public void given_WrongId_whenFindSeanceById_thenThrowCorrectException() {
        int wrongId = 1;
        when(seanceRepository.findById(wrongId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> {
            seanceService.findSeanceById(wrongId);
        });
        verify(seanceRepository, times(1)).findById(wrongId);
    }

    @Test
    public void givenSeance_whenFindSeanceById_thenReturnCorrectSeance() {
        Seance seance = new Seance();
        seance.setRoom(new Room());
        seance.setMovie(new Movie());
        seance.setTicketsAvailable(100);
        seance.setId(1);
        seance.setStartTime(LocalDateTime.of(2023,8,12,14,30,45));
        seance.setEndTime(LocalDateTime.of(2024,11,6,8,15,23));

        when(seanceRepository.save(seance)).thenReturn(seance);
        Seance createdSeance = seanceService.createSeance(seance);

        assertNotNull(createdSeance);
        assertNotNull(createdSeance.getRoom());
        assertNotNull(createdSeance.getMovie());
        assertEquals(1, createdSeance.getId(),"The seance id should be 1 but is not!");
        assertEquals(100, createdSeance.getTicketsAvailable(), "The seance tickets available should be 100 but is not!");
        assertEquals(LocalDateTime.of(2023,8,12,14,30,45), createdSeance.getStartTime());
        assertEquals(LocalDateTime.of(2024,11,6,8,15,23), createdSeance.getEndTime());
        verify(seanceRepository, times(1)).save(seance);
    }

    @Test
    public void givenNewSeance_whenUpdateSeance_thenReturnCorrectSeance() {
        Seance seance = new Seance();
        seance.setRoom(null);
        seance.setMovie(null);
        seance.setTicketsAvailable(100);
        seance.setId(1);
        seance.setStartTime(LocalDateTime.of(2023,8,12,14,30,45));
        seance.setEndTime(LocalDateTime.of(2024,11,6,8,15,23));

        Seance newSeance = new Seance();
        newSeance.setTicketsAvailable(200);
        newSeance.setRoom(new Room());
        newSeance.setMovie(new Movie());
        newSeance.setStartTime(LocalDateTime.of(2025,4,12,3,15,45));
        newSeance.setEndTime(LocalDateTime.of(2023,2,1,3,12,14));

        when(seanceRepository.findById(1)).thenReturn(Optional.of(seance));
        when(seanceRepository.save(seance)).thenReturn(seance);
        Seance updatedSeance = seanceService.updateSeance(newSeance, 1);

        assertNotNull(updatedSeance);
        assertNotNull(updatedSeance.getRoom());
        assertNotNull(updatedSeance.getMovie());
        assertEquals(1, updatedSeance.getId(), "The seance id should be 1 but is not!");
        assertEquals(200, updatedSeance.getTicketsAvailable(), "The seance tickets available should be 200 but is not!");
        assertEquals(LocalDateTime.of(2025,4,12,3,15,45), updatedSeance.getStartTime());
        assertEquals(LocalDateTime.of(2023,2,1,3,12,14), updatedSeance.getEndTime());
        verify(seanceRepository, times(1)).save(seance);
        verify(seanceRepository, times(1)).findById(1);
    }

    @Test
    public void givenCorrectId_whenDeleteSeanceById_thenDeleteEntity() {
        int correctId = 1;
        Seance seance = new Seance();
        seance.setId(correctId);

        when(seanceRepository.findById(correctId)).thenReturn(Optional.of(seance));
        seanceService.deleteSeanceById(correctId);

        verify(seanceRepository,times(1)).findById(correctId);
        verify(seanceRepository, times(1)).deleteById(correctId);
    }

    @Test
    public void givenWrongId_whenDeleteSeancesById_thenThrowCorrectException() {
        int wrongId = 1;

        when(seanceRepository.findById(wrongId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            seanceService.deleteSeanceById(wrongId);
        });

        verify(seanceRepository, times(1)).findById(wrongId);
    }

    @Test
    public void givenSeance_whenSeanceDTOMapper_thenReturnSeanceDTO() {
        Movie movie = new Movie();
        movie.setId(1);

        Room room = new Room();
        room.setId(1);
        room.setSeatsNumber(100);

        Seance seance = new Seance();
        seance.setId(1);
        seance.setMovie(movie);
        seance.setRoom(room);
        seance.setTicketsAvailable(room.getSeatsNumber());
        seance.setStartTime(LocalDateTime.of(2023,8,12,14,30,45));
        seance.setEndTime(LocalDateTime.of(2024,11,6,8,15,23));

        SeanceDTO seanceDTO = seanceService.seanceDTOMapper(seance);

        assertNotNull(seanceDTO);
        assertInstanceOf(SeanceDTO.class, seanceDTO);
        assertEquals(1, seanceDTO.getId(),"The seanceDTO id should be 1 but is not");
        assertEquals(1, seanceDTO.getMovieId(), "The seanceDTO movieId should be 1 but is not!");
        assertEquals(1, seanceDTO.getRoomId(), "The seanceDTO roomId should be 1 but is not!");
        assertEquals(100, seanceDTO.getTicketsAvailable(), "The seanceDTO ticketsAvailable should be 100 but is not!");
        assertEquals(LocalDateTime.of(2023,8,12,14,30,45), seanceDTO.getStartTime());
        assertEquals(LocalDateTime.of(2024,11,6,8,15,23), seanceDTO.getEndTime());
    }
}
