package ro.tuiasi.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.students.models.Address;

public interface AddressRepository extends JpaRepository<Address, Integer>
{
}
