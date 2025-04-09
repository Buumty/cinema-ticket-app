package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews.MovieView;

public class UserFooter extends HorizontalLayout {
    public UserFooter() {
        setWidthFull();
        setHeight("10%");
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(new Span("User Footer"));
        add(new RouterLink("Admin Panel", MovieView.class));
    }
}
