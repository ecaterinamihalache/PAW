package ro.tuiasi.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.students.models.Accommodation;

public interface AccommodationsRepository extends JpaRepository<Accommodation, Integer>
{
}
