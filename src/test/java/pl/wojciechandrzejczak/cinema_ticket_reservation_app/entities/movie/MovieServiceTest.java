package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;
    private MovieService movieService;

    @BeforeEach
    private void setUp() {
        this.movieService = new MovieService(movieRepository);
    }

    @Test
    public void givenNoMovie_whenFindAllMovies_thenReturnEmptyList() {
        Mockito.when(movieRepository.findAll()).thenReturn(emptyList());
        List<Movie> allMovies = movieService.findAllMovies();

        assertNotNull(allMovies);
        assertEquals(0, allMovies.size(), "The list size should be 0 but is not!");
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void givenOneMovie_whenFindAllMovies_thenReturnListOfOneEntity() {
        Mockito.when(movieRepository.findAll()).thenReturn(List.of(new Movie()));
        List<Movie> allMovies = movieService.findAllMovies();

        assertNotNull(allMovies);
        assertEquals(1, allMovies.size(), "The list size should be 1 but is not!");
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void givenMultipleMovies_whenFindAllMovies_thenReturnCorrectList() {
        Mockito.when(movieRepository.findAll()).thenReturn(List.of(new Movie(), new Movie(), new Movie()));
        List<Movie> allMovies = movieService.findAllMovies();

        assertNotNull(allMovies);
        assertEquals(3, allMovies.size(), "The list size should be 1 but is not!");
        verify(movieRepository, times(1)).findAll();
    }

    @Test
    public void givenCorrectId_whenFindMovieById_thenReturnCorrectMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("Indiana Jones");
        movie.setDescription("Adventure");
        movie.setLength(120);
        movie.setSeance(new Seance());

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        Movie movieById = movieService.findMovieById(1);

        assertNotNull(movieById);
        assertNotNull(movieById.getSeance());
        assertEquals(1, movieById.getId(), "The movie ID should be 1 but is not!");
        assertEquals("Indiana Jones", movieById.getName(), "The movie name should be 'Indiana Jones' but is not!");
        assertEquals("Adventure", movieById.getDescription(), "The movie description should be 'Adventure' but is not!");
        assertEquals(120, movieById.getLength(), "The movie length should be 120 but is not!");
        verify(movieRepository, times(1)).findById(1);
    }

    @Test
    public void givenWrongId_whenFindMovieById_thenThrowCorrectException() {
        int wrongId = 1;

        when(movieRepository.findById(wrongId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            movieService.findMovieById(wrongId);
        });
        verify(movieRepository, times(1)).findById(wrongId);
    }

    @Test
    public void givenMovie_whenCreateMovie_returnCreatedMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("Indiana Jones");
        movie.setDescription("Adventure");
        movie.setLength(120);
        movie.setSeance(new Seance());

        when(movieRepository.save(movie)).thenReturn(movie);
        Movie createdMovie = movieService.createMovie(movie);

        assertNotNull(createdMovie);
        assertNotNull(createdMovie.getSeance());
        assertEquals(1, createdMovie.getId(), "The movie ID should be 1 but is not!");
        assertEquals("Indiana Jones", createdMovie.getName(), "The movie name should be 'Indiana Jones' but is not!");
        assertEquals("Adventure", createdMovie.getDescription(), "The movie description should be 'Adventure' but is not!");
        assertEquals(120, createdMovie.getLength(), "The movie length should be 120 but is not!");
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void givenNewMovie_whenUpdateMovie_returnUpdatedMovie() {
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("Indiana Jones");
        movie.setDescription("Adventure");
        movie.setLength(120);
        movie.setSeance(null);

        Movie newMovie = new Movie();
        newMovie.setName("LOTR");
        newMovie.setDescription("Fantasy");
        newMovie.setLength(180);
        newMovie.setSeance(new Seance());

        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));
        when(movieRepository.save(movie)).thenReturn(movie);

        Movie updatedMovie = movieService.updateMovie(newMovie, 1);

        assertNotNull(updatedMovie);
        assertNotNull(updatedMovie.getSeance());
        assertEquals(1, updatedMovie.getId(), "The movie ID should be 1 but is not!");
        assertEquals("LOTR", updatedMovie.getName(), "The movie name should be 'LOTR' but is not!");
        assertEquals("Fantasy", updatedMovie.getDescription(), "The movie description should be 'Fantasy' but is not!");
        assertEquals(180, updatedMovie.getLength(), "The movie length should be 180 but is not!");
        verify(movieRepository, times(1)).findById(1);
        verify(movieRepository, times(1)).save(movie);
    }

    @Test
    public void givenCorrectId_whenDeleteMovieById_thenDeleteMovie() {
        int movieId = 1;
        Movie movie = new Movie();
        movie.setId(movieId);
        movie.setName("Indiana Jones");
        movie.setDescription("Adventure");
        movie.setLength(120);
        movie.setSeance(new Seance());

        when(movieRepository.findById(movieId)).thenReturn(Optional.of(movie));
        movieService.deleteMovieById(movieId);

        verify(movieRepository, times(1)).findById(movieId);
        verify(movieRepository, times(1)).deleteById(movieId);
    }

    @Test
    public void givenWrongId_whenDeleteMovieById_throwCorrectException() {
        int wrongId = 1;

        when(movieRepository.findById(wrongId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, ()-> {
            movieService.deleteMovieById(wrongId);
        });

        verify(movieRepository,times(1)).findById(wrongId);
    }
}
