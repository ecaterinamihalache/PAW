package ro.tuiasi.students.interfaces;

import ro.tuiasi.students.models.Setup;
import ro.tuiasi.students.models.Student;

import java.util.List;

public interface StudentServiceInterface
{
    Student createStudent(Student student, Integer Id);
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    void deleteById(Integer id);
    Setup getRoommates(Integer studentId);
    List<Student> getAccommodatedStudents();
}
