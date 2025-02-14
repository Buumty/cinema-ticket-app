package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class RoomService {
    private final RoomRepository roomRepository;

    @Autowired
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAllRooms() {
        return roomRepository.findAll();
    }

    public Room findRoomById(Integer id) {
        return roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Room room,Integer id) {
        Room dbRoom = roomRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        if (room.getName() != null) {
            dbRoom.setName(room.getName());
        }

        if (room.getSeatsNumber() != null) {
            dbRoom.setSeatsNumber(room.getSeatsNumber());
        }

        return roomRepository.save(dbRoom);
    }

    public void deleteRoomById(Integer id) {
        if (roomRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        roomRepository.deleteById(id);
    }
}
