package ro.tuiasi.students.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.tuiasi.students.models.StudentInfo;

public interface StudentInfoRepository extends JpaRepository<StudentInfo, Integer>
{
}
