package pl.wojciechandrzejczak.cinema_ticket_reservation_app;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.server.StreamResource;
import org.springframework.stereotype.Service;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeat;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.reservedseat.ReservedSeatRepository;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.SeatButton;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

@Service
public class ReservationService {
    private  ReservedSeatRepository reservedSeatRepository;

    public ReservationService(ReservedSeatRepository reservedSeatRepository) {
        this.reservedSeatRepository = reservedSeatRepository;
    }

    public void reserveSelectedTickets(Seance seance, List<SeatButton> selectedSeats, Map<SeatButton, Ticket> selectedTickets) {
        if (seance == null) return;

        for (SeatButton selectedSeat : selectedSeats) {
            String[] seatPosition = selectedSeat.getText().split("-");
            int row = Integer.parseInt(seatPosition[0]) - 1;
            int seat = Integer.parseInt(seatPosition[1]) - 1;
            reserveTicket(new ReservedSeat(seance, row, seat));
        }
    }
    public byte[] generatePdfTicket(Ticket ticket, Seance seance, String seat) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        document.add(new Paragraph("Ticket for Seance: " + seance.getMovie().getName()));
        document.add(new Paragraph("Seat: " + seat));
        document.add(new Paragraph("Ticket Type: " + ticket.getType()));
        document.add(new Paragraph("Price: " + ticket.getPrice()));
        System.out.println("Document content added");

        document.close();

        return outputStream.toByteArray();
    }
    private void reserveTicket(ReservedSeat reservedSeat) {
        reservedSeatRepository.save(reservedSeat);
    }
    public Anchor createDownloadLink(Ticket ticket, Seance seance, String seat) {
        try {
            byte[] pdfBytes = generatePdfTicket(ticket, seance, seat);

            StreamResource resource = new StreamResource("ticket_" + ticket.getId() + ".pdf",
                    () -> new ByteArrayInputStream(pdfBytes));
            resource.setContentType("application/pdf");

            return new Anchor(resource, "Download Ticket");
        } catch (Exception e) {
            e.printStackTrace();
            return new Anchor("#", "Error generating ticket");
        }
    }

}
