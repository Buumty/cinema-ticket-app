package pl.wojciechandrzejczak.cinema_ticket_reservation_app.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Controller
public class MovieController {
    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movie/{id}")
    public ResponseEntity<Movie> findMovieById(@PathVariable Integer id) {
        Movie movieById = movieService.findMovieById(id);

        return ResponseEntity.ok(movieById);
    }

    @PostMapping("/movie")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        Movie createdMovie = movieService.createMovie(movie);

        URI location = URI.create("/movie/" + createdMovie.getId());

        return ResponseEntity.created(location).body(createdMovie);
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Movie> updateMovie(@RequestBody Movie movie, @PathVariable Integer id) {
        Movie updatedMovie = movieService.updateMovie(movie, id);

        return ResponseEntity.ok(updatedMovie);
    }

    @PatchMapping("/movie/{id}")
    public ResponseEntity<Movie> partiallyUpdateMovie(@RequestBody Movie movie, @PathVariable Integer id) {
        Movie partiallyUpdatedMovie = movieService.updateMovie(movie, id);

        return ResponseEntity.ok(partiallyUpdatedMovie);
    }

    @DeleteMapping("movie/{id}")
    public ResponseEntity<Void> deleteMovieById(@PathVariable Integer id) {
        movieService.deleteMovieById(id);

        return ResponseEntity.noContent().build();
    }
}
