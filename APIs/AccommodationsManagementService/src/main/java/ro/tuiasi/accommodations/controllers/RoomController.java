package ro.tuiasi.accommodations.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuiasi.accommodations.models.Room;
import ro.tuiasi.accommodations.interfaces.RoomServiceInterface;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/accommodations-service/accommodations/rooms")
public class RoomController
{
    @Autowired
    private RoomServiceInterface roomService;

    @GetMapping("")
    public ResponseEntity<List<Room>> getAllRooms()
    {
        try
        {
            return new ResponseEntity<>(roomService.getAllRooms(), HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{accommodationId}")
    public ResponseEntity<List<Room>> getAllAccommodationRooms(@PathVariable int accommodationId)
    {
        try
        {
            List<Room> listRooms = roomService.getAllAccommodationRooms(accommodationId);
            if (listRooms.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listRooms, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}
