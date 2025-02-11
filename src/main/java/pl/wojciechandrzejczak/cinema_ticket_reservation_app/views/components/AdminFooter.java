package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class AdminFooter extends HorizontalLayout {
    public AdminFooter() {
        setWidthFull();
        setHeight("10%");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new Span("Admin Footer"));
    }
}
