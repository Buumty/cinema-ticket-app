package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance;

import java.time.LocalDateTime;

public class SeanceDTO {
    private Integer id;
    private Integer movieId;
    private Integer roomId;
    private Integer ticketsAvailable;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public SeanceDTO() {}

    public SeanceDTO(Integer id, Integer movieId, Integer roomId, Integer ticketsAvailable, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.movieId = movieId;
        this.roomId = roomId;
        this.ticketsAvailable = ticketsAvailable;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getTicketsAvailable() {
        return ticketsAvailable;
    }

    public void setTicketsAvailable(Integer ticketsAvailable) {
        this.ticketsAvailable = ticketsAvailable;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "SeanceDTO{" +
                "id=" + id +
                ", movie=" + movieId +
                ", room=" + roomId +
                ", ticketsAvailable=" + ticketsAvailable +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
