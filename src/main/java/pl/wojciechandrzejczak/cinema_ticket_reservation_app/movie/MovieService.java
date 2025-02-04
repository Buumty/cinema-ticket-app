package pl.wojciechandrzejczak.cinema_ticket_reservation_app.movie;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MovieService {

    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieById(Integer id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie movie, Integer id) {
        Movie dbMovie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (movie.getName() != null) {
            dbMovie.setName(movie.getName());
        }
        if (movie.getLength() != null) {
            dbMovie.setLength(movie.getLength());
        }
        if (movie.getStartTime() != null) {
            dbMovie.setStartTime(movie.getStartTime());
        }
        if (movie.getDescription() != null) {
            dbMovie.setDescription(movie.getDescription());
        }

        return movieRepository.save(dbMovie);
    }

    public void deleteMovieById(Integer id) {
        movieRepository.deleteById(id);
    }
}
