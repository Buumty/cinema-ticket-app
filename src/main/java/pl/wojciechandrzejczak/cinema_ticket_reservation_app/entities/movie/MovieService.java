package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> findAllMovies() {
        return movieRepository.findAll();
    }

    public Movie findMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie, java.lang.Integer id) {
        Movie dbMovie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (movie.getName() != null) {
            dbMovie.setName(movie.getName());
        }
        if (movie.getLength() != null) {
            dbMovie.setLength(movie.getLength());
        }
        if (movie.getDescription() != null) {
            dbMovie.setDescription(movie.getDescription());
        }
        if (movie.getSeance() != null) {
            dbMovie.setSeance(movie.getSeance());
        }

        return movieRepository.save(dbMovie);
    }

    public void deleteMovieById(Integer id) {
        if (movieRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        movieRepository.deleteById(id);
    }
}
