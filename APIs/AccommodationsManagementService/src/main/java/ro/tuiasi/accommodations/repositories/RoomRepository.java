package ro.tuiasi.accommodations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.accommodations.models.Room;

public interface RoomRepository extends JpaRepository<Room, Integer>
{
}
