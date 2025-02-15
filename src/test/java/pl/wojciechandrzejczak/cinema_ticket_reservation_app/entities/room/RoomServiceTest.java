package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    private RoomService roomService;

    @BeforeEach
    public void setUp() {
        this.roomService = new RoomService(roomRepository);
    }

    @Test
    public void givenNoRooms_whenFindAllRooms_thenReturnEmptyList() {
        Mockito.when(roomRepository.findAll()).thenReturn(emptyList());
        List<Room> allRooms = roomService.findAllRooms();

        assertNotNull(allRooms);
        assertTrue(allRooms.isEmpty(), "The list should be empty but is not!");
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void givenOneRoom_whenFindAllRooms_thenReturnListWithOneEntity() {
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(new Room(5, 10, "Sapphire")));
        List<Room> allRooms = roomService.findAllRooms();

        assertNotNull(allRooms);
        assertEquals(1, allRooms.size(), "The list should be the size of one!");
        assertEquals(5, allRooms.get(0).getRows(), "There should be 5 rows but is not!");
        assertEquals(10, allRooms.get(0).getColumns(), "There should be 10 columns but is not!");
        assertEquals("Sapphire", allRooms.get(0).getName(), "The name should be 'Sapphire' but is not!");
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void givenMultipleRooms_whenFindAllRooms_thenReturnListWithCorrectSize() {
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(new Room(), new Room(), new Room(), new Room()));

        List<Room> allRooms = roomService.findAllRooms();

        assertNotNull(allRooms);
        assertEquals(4, allRooms.size());
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void givenCorrectId_whenFindRoomById_thenReturnCorrectRoom() {
        Room room = new Room();
        room.setId(1);
        room.setName("Sapphire");
        room.setColumns(10);
        room.setRows(5);

        Mockito.when(roomRepository.findById(1)).thenReturn(Optional.of(room));
        Room roomById = roomService.findRoomById(1);

        assertNotNull(roomById);
        assertEquals(1, roomById.getId(), "The ID of room should be 1 but is not!");
        assertEquals("Sapphire", roomById.getName(), "The room name should be 'Sapphire' but is not!");
        assertEquals(5, roomById.getRows(), "There should be 5 rows but is not!");
        assertEquals(10, roomById.getColumns(), "There should be 10 columns but is not!");
        verify(roomRepository, times(1)).findById(1);
    }

    @Test
    public void givenWrongId_whenFindRoomById_thenThrowCorrectException() {
        int wrongId = 19;

        Mockito.when(roomRepository.findById(wrongId)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> {
            roomService.findRoomById(wrongId);
        });
        verify(roomRepository, Mockito.times(1)).findById(wrongId);
    }

    @Test
    public void givenRoom_whenCreateRoom_thenReturnCorrectRoom() {
        Room room = new Room();
        room.setId(1);
        room.setName("Sapphire");
        room.setRows(5);
        room.setColumns(10);

        Mockito.when(roomRepository.save(room)).thenReturn(room);
        Room createdRoom = roomService.createRoom(room);

        assertNotNull(createdRoom);
        assertEquals(1, createdRoom.getId(), "The ID of room should be 1 but is not!");
        assertEquals("Sapphire", createdRoom.getName(), "The room name should be 'Sapphire' but is not!");
        assertEquals(5, createdRoom.getRows(), "There should be 5 rows but is not!");
        assertEquals(10, createdRoom.getColumns(), "There should be 10 columns but is not!");
        verify(roomRepository, Mockito.times(1)).save(room);
    }

    @Test
    public void givenNewRoom_whenUpdateRoom_thenReturnUpdatedRoom() {
        Room existingRoom = new Room();
        existingRoom.setId(1);
        existingRoom.setName("Emerald");
        existingRoom.setRows(5);
        existingRoom.setColumns(10);

        Room newRoom = new Room();
        newRoom.setName("Sapphire");
        newRoom.setRows(10);
        newRoom.setColumns(20);

        when(roomRepository.findById(1)).thenReturn(Optional.of(existingRoom));
        when(roomRepository.save(existingRoom)).thenReturn(existingRoom);
        Room updatedRoom = roomService.updateRoom(newRoom, 1);

        assertNotNull(updatedRoom);
        assertEquals(1, updatedRoom.getId(), "The ID of updated room should be 1 but is not!");
        assertEquals("Sapphire", updatedRoom.getName(), "The room name should be 'Sapphire' but is not!");
        assertEquals(10, updatedRoom.getRows(), "There should be 10 rows but is not!");
        assertEquals(20, updatedRoom.getColumns(), "There should be 20 columns but is not!");
        verify(roomRepository, times(1)).findById(1);
        verify(roomRepository, times(1)).save(existingRoom);
    }

    @Test
    public void givenCorrectId_whenDeleteRoomById_thenRoomIsDeleted() {
        int roomId = 1;
        Room room = new Room();
        room.setId(roomId);
        room.setName("Sapphire");
        room.setRows(5);
        room.setColumns(10);

        Mockito.when(roomRepository.findById(roomId)).thenReturn(Optional.of(room));
        roomService.deleteRoomById(roomId);

        verify(roomRepository, times(1)).findById(roomId);
        verify(roomRepository, times(1)).deleteById(roomId);
    }

    @Test
    public void givenWrongId_whenDeleteRoomById_thenThrowCorrectException() {
        int wrongId = 1;

        Mockito.when(roomRepository.findById(wrongId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> {
            roomService.deleteRoomById(wrongId);
        });
        verify(roomRepository, times(1)).findById(wrongId);
    }
}
