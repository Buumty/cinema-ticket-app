package pl.wojciechandrzejczak.cinema_ticket_reservation_app.views.adminviews;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.DataProvider;
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
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSizeFull();
        horizontalLayout.add(setUpRoomGrid());
        horizontalLayout.add(setUpRoomDataEntry());

        add(horizontalLayout);
    }

    private Grid<Room> setUpRoomGrid() {
        roomGrid = new Grid<>(Room.class,false);
        roomGrid.setWidth("70%");
        roomGrid.setHeightFull();

        roomGrid.addColumn(Room::getName).setHeader("Name");
        roomGrid.addColumn(Room::getSeatsNumber).setHeader("Seats number");

        roomGrid.setItems(DataProvider.ofCollection(roomService.findAllRooms()));

        return roomGrid;
    }

    private VerticalLayout setUpRoomDataEntry() {
        VerticalLayout roomDataEntryLayout = new VerticalLayout();
        roomDataEntryLayout.setWidth("30%");
        roomDataEntryLayout.setHeightFull();

        TextField name = new TextField("Name");
        NumberField seatsNumber = new NumberField("Seats number");

        roomDataEntryLayout.add(name);
        roomDataEntryLayout.add(seatsNumber);
        roomDataEntryLayout.add(setUpSaveRoomButton(name,seatsNumber));
        roomDataEntryLayout.add(setUpDeleteRoomButton());

        return roomDataEntryLayout;
    }

    private Button setUpSaveRoomButton(TextField name, NumberField seatsNumber) {
        Button saveRoom = new Button("Save room");

        saveRoom.addClickListener(buttonClickEvent-> {
            roomService.createRoom(new Room(seatsNumber.getValue().intValue(),name.getValue()));
            roomGrid.setItems(roomService.findAllRooms());
        });

        return saveRoom;
    }

    private Button setUpDeleteRoomButton() {
        Button deleteRoom = new Button("Delete room");

        deleteRoom.addClickListener(buttonClickEvent -> {
            Room selectedRoom = roomGrid.asSingleSelect().getValue();
            if (selectedRoom != null) {
                roomService.deleteRoomById(selectedRoom.getId());
                roomGrid.setItems(roomService.findAllRooms());
            } else {
                Notification.show("No room selected");
            }
        });

        return deleteRoom;
    }
}
