package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
@Transactional
public class TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> findAllTickets() {
        return ticketRepository.findAll();
    }

    public Ticket findTicketById(Integer id) {
        return ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Ticket createTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket updateTicket(Ticket ticket, Integer id) {
        Ticket dbTicket = ticketRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (ticket.getPrice() != null) {
            dbTicket.setPrice(ticket.getPrice());
        }
        if (ticket.getType() != null) {
            dbTicket.setType(ticket.getType());
        }

        return ticketRepository.save(dbTicket);
    }

    public void deleteTicketById(Integer id) {
        if (ticketRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        ticketRepository.deleteById(id);
    }

    public void generatePdfTicket(Ticket ticket, Seance seance, String seat) throws Exception {
        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, outputStream);
        document.open();

        // Dodawanie danych do PDF
        document.add(new Paragraph("Ticket for Seance: " + seance.getMovie().getName()));
        document.add(new Paragraph("Seat: " + seat));
        document.add(new Paragraph("Ticket Type: " + ticket.getType()));
        document.add(new Paragraph("Price: " + ticket.getPrice()));
        System.out.println("Document content added");

        document.close();

        String ticketDirectory = "C:/Users/wojtas/Desktop/Projekty-Java/advanced/tickets-directory";  // Zmień na odpowiednią ścieżkę na Twoim serwerze
        File directory = new File(ticketDirectory);
        if (!directory.exists()) {
            directory.mkdirs();  // Tworzy katalog, jeśli nie istnieje
        }

        // Zapisz plik PDF
        try (FileOutputStream fos = new FileOutputStream("ticket_" + UUID.randomUUID() + ".pdf")) {
            fos.write(outputStream.toByteArray());
            System.out.println("ticket_" + ticket.getId() + ".pdf");
            System.out.println("PDF saved sucessfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        }
    }
}
