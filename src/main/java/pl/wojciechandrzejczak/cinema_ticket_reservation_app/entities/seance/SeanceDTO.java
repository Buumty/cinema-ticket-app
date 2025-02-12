package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance;

import java.time.LocalDateTime;

public class SeanceDTO {
    private Integer id;
    private String movieName;
    private String roomName;
    private Integer ticketsAvailable;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    public SeanceDTO() {}

    public SeanceDTO(Integer id, String movieName, String roomName, Integer ticketsAvailable, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.movieName = movieName;
        this.roomName = roomName;
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

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
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
                ", movie=" + movieName +
                ", room=" + roomName +
                ", ticketsAvailable=" + ticketsAvailable +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
