package ro.tuiasi.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.students.models.Preference;

public interface PreferencesRepository extends JpaRepository<Preference, Integer>
{
}
