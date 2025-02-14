package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews.MovieView;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews.RoomView;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews.SeanceView;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews.TicketView;

public class AdminHeader extends HorizontalLayout {
    public AdminHeader() {
        setWidthFull();
        setHeight("10%");
        setSpacing(true);
        setAlignItems(Alignment.CENTER);
        HorizontalLayout verticalLayout = new HorizontalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setJustifyContentMode(JustifyContentMode.AROUND);
        verticalLayout.add(new RouterLink("Movie", MovieView.class));
        verticalLayout.add(new RouterLink("Room", RoomView.class));
        verticalLayout.add(new RouterLink("Ticket", TicketView.class));
        verticalLayout.add(new RouterLink("Seance", SeanceView.class));
        verticalLayout.add(new Span("Logout"));
        add(verticalLayout);
    }
}
