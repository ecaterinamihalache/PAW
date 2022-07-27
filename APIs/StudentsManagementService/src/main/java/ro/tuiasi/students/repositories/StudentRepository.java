package ro.tuiasi.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ro.tuiasi.students.models.Student;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>
{

    @Query(value = "SELECT st FROM Student st ORDER BY st.year_grade DESC")
    List<Student> findAllOrderByYearGradeDesc();

    @Transactional
    @Modifying
    @Query(value = "UPDATE Student st set st.rooms_id = ?1, st.roommates = ?2 where st.id = ?3")
    int setRepartitionForStudent(Integer rooms_id, String roommates, Integer id);

    @Query(value = "SELECT * FROM students WHERE rooms_id IS NOT NULL", nativeQuery = true)
    List<Student> getAccommodatedStudents();
}
