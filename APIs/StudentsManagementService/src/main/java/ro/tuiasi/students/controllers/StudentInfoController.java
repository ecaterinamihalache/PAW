package ro.tuiasi.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuiasi.students.models.StudentInfo;
import ro.tuiasi.students.interfaces.StudentInfoServiceInterface;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student-management-service/accommodations")
public class StudentInfoController
{
    @Autowired
    private StudentInfoServiceInterface studentInfoService;

    @GetMapping("/students-details")
    public ResponseEntity<List<StudentInfo>> getAllStudentsInfo()
    {
        try
        {
            List<StudentInfo> listStudents = studentInfoService.getAllStudentsInfo();
            if (listStudents.isEmpty())
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(listStudents, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/students-details/{id}")
    public ResponseEntity<StudentInfo> getStudentInfo(@PathVariable(value = "id") Integer id)
    {
        try
        {
            StudentInfo student = studentInfoService.getStudentInfoById(id);
            if(student == null)
            {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(student, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/students-details/{id}")
    public ResponseEntity<HttpStatus> deleteStudentInfo(@PathVariable("id") Integer id) {
        try
        {
            studentInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students/{id}/students-details")
    public ResponseEntity<StudentInfo> createStudentInfo(@RequestBody StudentInfo studentInfo, @PathVariable(value = "id") Integer id) {
        try
        {
            StudentInfo _studentInfo = studentInfoService.createStudentInfo(studentInfo, id);
            return new ResponseEntity<>(_studentInfo, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }
}

