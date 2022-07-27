package ro.tuiasi.students.interfaces;

import ro.tuiasi.students.models.Student;
import ro.tuiasi.students.models.StudentInfo;

import java.util.List;

public interface StudentInfoServiceInterface
{
    List<StudentInfo> getAllStudentsInfo();
    StudentInfo createStudentInfo(StudentInfo studentInfo, Integer id);
    StudentInfo getStudentInfoById(Integer id);
    void deleteById(Integer id);
}
