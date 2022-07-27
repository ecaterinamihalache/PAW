package ro.tuiasi.students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.students.models.StudentInfo;
import ro.tuiasi.students.repositories.StudentInfoRepository;
import ro.tuiasi.students.interfaces.StudentInfoServiceInterface;

import java.util.List;

@Service
public class StudentInfoService implements StudentInfoServiceInterface
{
    @Autowired
    private StudentInfoRepository studentInfoRepository;

    @Override
    public StudentInfo createStudentInfo(StudentInfo studentInfo, Integer id)
    {
        try
        {
            StudentInfo _studentInfo = new StudentInfo();
            _studentInfo.setId(id);
            _studentInfo.setUniversity(studentInfo.getUniversity());
            _studentInfo.setFaculty(studentInfo.getFaculty());
            _studentInfo.setSpecialization(studentInfo.getSpecialization());

            return studentInfoRepository.save(_studentInfo);
        }
        catch (Exception e)
        {
            throw e;
        }
    }
    @Override
    public List<StudentInfo> getAllStudentsInfo()
    {
        return studentInfoRepository.findAll();
    }

    @Override
    public StudentInfo getStudentInfoById(Integer id)
    {
        return studentInfoRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id)
    {
        studentInfoRepository.deleteById(id);
    }
}
