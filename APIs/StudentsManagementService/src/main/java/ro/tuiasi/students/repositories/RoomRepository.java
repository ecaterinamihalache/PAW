package ro.tuiasi.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.students.models.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>
{
}
