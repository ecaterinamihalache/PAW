package ro.tuiasi.students.interfaces;

import org.springframework.http.ResponseEntity;
import ro.tuiasi.students.models.Room;

import java.util.List;

public interface AccommodationsApiRequestServiceInterface {
    ResponseEntity<Room[]> getAllRoomsRequest();
}
