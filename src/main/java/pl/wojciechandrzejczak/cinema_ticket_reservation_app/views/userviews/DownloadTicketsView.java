//package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;
//
//import com.vaadin.flow.component.html.Anchor;
//import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
//import com.vaadin.flow.component.orderedlayout.VerticalLayout;
//import com.vaadin.flow.router.Route;
//import com.vaadin.flow.server.StreamResource;
//import org.checkerframework.checker.units.qual.A;
//import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.seance.Seance;
//import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.Ticket;
//import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketDetails;
//import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.ticket.TicketService;
//import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.UserHeader;
//
//import java.io.ByteArrayInputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//@Route("download-tickets")
//public class DownloadTicketsView extends VerticalLayout {
//    private TicketService ticketService;
//
//    public DownloadTicketsView(TicketService ticketService) {
//        this.ticketService = ticketService;
//        setSizeFull();
//        HorizontalLayout horizontalLayout = new HorizontalLayout();
//        horizontalLayout.setSizeFull();
//        horizontalLayout.setAlignItems(Alignment.CENTER);
//        horizontalLayout.setJustifyContentMode(JustifyContentMode.CENTER);
//        horizontalLayout.add(createDownloadLink());
//        add(new UserHeader());
//        add(horizontalLayout);
//    }
//    private List<Anchor> createDownloadLink(List<TicketDetails> ticketDetailsList) {
//        List<Anchor> anchors = new ArrayList<>();
//        try {
//            for (TicketDetails ticketDetails : ticketDetailsList) {
//                byte[] pdfBytes = ticketService.generatePdfTicket(ticketDetails.getTicket(), ticketDetails.getSeance(), ticketDetails.getSeat());
//                StreamResource resource = new StreamResource("ticket_" + ticketDetails.getSeance().getMovie().getName() + "_" + ticketDetails.getSeat() + ".pdf",
//                        () -> new ByteArrayInputStream(pdfBytes));
//                resource.setContentType("application/pdf");
//                anchors.add(new Anchor(resource, "Download Ticket"));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return anchors;
//    }
//}
