package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.userviews;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminHeader;

@UIScope
@Route("")
public class MainView extends VerticalLayout {
    @Autowired
    public MainView() {
        add(new AdminHeader());
    }
}
