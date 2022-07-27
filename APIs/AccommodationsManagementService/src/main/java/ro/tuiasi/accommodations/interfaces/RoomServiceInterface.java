package ro.tuiasi.accommodations.interfaces;

import ro.tuiasi.accommodations.models.Room;

import java.util.List;

public interface RoomServiceInterface
{
    List<Room> getAllRooms();
    List<Room> getAllAccommodationRooms(Integer id);
}
