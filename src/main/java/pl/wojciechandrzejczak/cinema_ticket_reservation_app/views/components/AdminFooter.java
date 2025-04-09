package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews.MainView;

public class AdminFooter extends HorizontalLayout {
    public AdminFooter() {
        setWidthFull();
        setHeight("10%");
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new Span("Admin Footer"));
        add(new RouterLink("User Panel", MainView.class));
    }
}
