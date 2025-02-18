package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.movie;

import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private java.lang.Integer id;
    private String name;
    @Column(nullable = false)
    private Integer length;
    private String description;
    public Movie(){}

    public Movie(String name, Integer length, String description) {
        this.name = name;
        this.length = length;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer lengthInMinutes) {
        this.length = lengthInMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", length=" + length +
                ", description='" + description + '\'' +
                '}';
    }
}
