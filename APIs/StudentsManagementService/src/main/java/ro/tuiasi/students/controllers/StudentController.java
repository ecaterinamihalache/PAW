package ro.tuiasi.students.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuiasi.students.models.Setup;
import ro.tuiasi.students.models.Student;
import ro.tuiasi.students.interfaces.StudentServiceInterface;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/student-management-service/accommodations/students")
public class StudentController
{
    @Autowired
    private StudentServiceInterface studentService;

    @PostMapping("/{id}")
    public ResponseEntity<Student> createStudent(@RequestBody Student student, @PathVariable(value = "id") Integer id) {
        try
        {
            return new ResponseEntity<>(studentService.createStudent(student, id), HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents()
    {
        try
        {
            List<Student> listStudents = studentService.getAllStudents();
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

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Integer id)
    {
        try
        {
            Student student = studentService.getStudentById(id);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteStudent(@PathVariable("id") Integer id) {
        try
        {
            studentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/roommates")
    public ResponseEntity<Setup> getRoommates(@PathVariable("id") Integer id) {
        try {
            Setup setup = this.studentService.getRoommates(id);

            return new ResponseEntity<>(setup, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/accommodated")
    public ResponseEntity<List<Student>> getAccommodatedStudents() {
        try {
            List<Student> students = this.studentService.getAccommodatedStudents();

            if(students.isEmpty())
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(students, HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
