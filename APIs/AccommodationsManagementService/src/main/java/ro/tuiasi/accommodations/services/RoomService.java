package ro.tuiasi.accommodations.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.accommodations.models.Room;
import ro.tuiasi.accommodations.repositories.RoomRepository;
import ro.tuiasi.accommodations.interfaces.RoomServiceInterface;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService implements RoomServiceInterface
{
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAllRooms()
    {
        return roomRepository.findAll();
    }

    @Override
    public List<Room> getAllAccommodationRooms(Integer id)
    {
        List<Room> allRooms=roomRepository.findAll();
        List<Room> allRoomsForAnAccommodation = new ArrayList<>();
        for (int i=0; i<allRooms.size(); i++)
        {
            if(allRooms.get(i).getAccommodations_id() == id)
            {
                allRoomsForAnAccommodation.add(allRooms.get(i));
            }
        }
        return allRoomsForAnAccommodation;
    }
}
