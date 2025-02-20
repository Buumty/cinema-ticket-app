package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket;

import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

public class TicketDetails {
    private Ticket ticket;
    private Seance seance;
    private String seat;

    public TicketDetails(Ticket ticket, Seance seance, String seat) {
        this.ticket = ticket;
        this.seance = seance;
        this.seat = seat;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Seance getSeance() {
        return seance;
    }

    public void setSeance(Seance seance) {
        this.seance = seance;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }
}
