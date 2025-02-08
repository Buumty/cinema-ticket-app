package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Controller
public class RoomController {
    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping("/room")
    public ResponseEntity<List<Room>> findAllRooms() {
        List<Room> allRooms = roomService.findAllRooms();

        return ResponseEntity.ok(allRooms);
    }

    @GetMapping("/room/{id}")
    public ResponseEntity<Room> findRoomById(@PathVariable java.lang.Integer id) {
        Room roomById = roomService.findRoomById(id);

        return ResponseEntity.ok(roomById);
    }

    @PostMapping("/room")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = roomService.createRoom(room);

        URI location = URI.create("/room" + createdRoom.getId());

        return ResponseEntity.created(location).body(createdRoom);
    }

    @PutMapping("/room/{id}")
    public ResponseEntity<Room> updateRoom(@RequestBody Room room, @PathVariable java.lang.Integer id) {
        Room updatedRoom = roomService.updateRoom(room, id);

        return ResponseEntity.ok(updatedRoom);
    }

    @PatchMapping("/room/{id}")
    public ResponseEntity<Room> partiallyUpdateRoom(@RequestBody Room room, @PathVariable java.lang.Integer id) {
        Room partiallyUpdatedRoom = roomService.updateRoom(room, id);

        return ResponseEntity.ok(partiallyUpdatedRoom);
    }

    @DeleteMapping("/room/{id}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable java.lang.Integer id) {
        roomService.deleteRoomById(id);

        return ResponseEntity.noContent().build();
    }
}
