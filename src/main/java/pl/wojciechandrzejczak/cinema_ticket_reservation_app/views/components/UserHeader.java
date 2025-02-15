package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components;

import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.RouterLink;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews.HallView;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews.MainView;

public class UserHeader extends HorizontalLayout {
    public UserHeader() {
        setWidthFull();
        setHeight("10%");
        setSpacing(true);
        setAlignItems(FlexComponent.Alignment.CENTER);
        HorizontalLayout verticalLayout = new HorizontalLayout();
        verticalLayout.setSizeFull();
        verticalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        verticalLayout.add(new RouterLink("Repertoire", MainView.class));
        verticalLayout.add(new RouterLink("Buy a ticket", HallView.class));
        verticalLayout.add(new Span("Logout"));
        add(verticalLayout);
    }
}
