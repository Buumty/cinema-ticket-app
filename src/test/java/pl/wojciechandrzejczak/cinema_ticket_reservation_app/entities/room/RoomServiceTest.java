package pl.wojciechandrzejczak.cinema_ticket_reservation_app.entities.room;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    public void when_no_rooms_return_empty_list() {
        Mockito.when(roomRepository.findAll()).thenReturn(emptyList());
        List<Room> allRooms = roomService.findAllRooms();
        assertTrue(allRooms.isEmpty(),"The list should be empty but is not!");
    }

    @Test
    public void when_one_room_return_list_with_one_entity() {
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(new Room()));
        List<Room> allRooms = roomService.findAllRooms();
        assertEquals(1, allRooms.size(), "The list should be the size of one!");
    }

    @Test void when_multiple_rooms_return_list_with_correct_size() {
        Mockito.when(roomRepository.findAll()).thenReturn(List.of(new Room(),new Room(), new Room(), new Room()));
        List<Room> allRooms = roomService.findAllRooms();
        assertEquals(4, allRooms.size());
    }
}
