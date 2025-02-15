package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class UserFooter extends HorizontalLayout {
    public UserFooter() {
        setWidthFull();
        setHeight("10%");
        setAlignItems(FlexComponent.Alignment.CENTER);
        setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        add(new Span("User Footer"));
    }
}
