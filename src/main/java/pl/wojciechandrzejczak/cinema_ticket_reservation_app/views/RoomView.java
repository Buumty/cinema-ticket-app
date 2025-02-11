package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.Room;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room.RoomService;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminFooter;
import pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.components.AdminHeader;

@UIScope
@Route("room-list")
public class RoomView extends VerticalLayout {
    private final RoomService roomService;
    private Grid<Room> roomGrid;

    @Autowired
    public RoomView(RoomService roomService) {
        this.roomService = roomService;
        setPadding(true);
        setSpacing(true);
        setSizeFull();
        add(new AdminHeader());
        mainBody();
        add(new AdminFooter());
    }

    private void mainBody() {
    }
}
