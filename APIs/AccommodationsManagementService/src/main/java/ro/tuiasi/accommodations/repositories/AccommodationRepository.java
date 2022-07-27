package ro.tuiasi.accommodations.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.accommodations.models.Accommodation;

public interface AccommodationRepository extends JpaRepository<Accommodation, Integer>
{
}
