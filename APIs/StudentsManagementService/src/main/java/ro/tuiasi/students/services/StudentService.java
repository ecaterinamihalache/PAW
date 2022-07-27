package ro.tuiasi.students.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.students.models.Room;
import ro.tuiasi.students.models.Setup;
import ro.tuiasi.students.models.Student;
import ro.tuiasi.students.repositories.AccommodationsRepository;
import ro.tuiasi.students.repositories.RoomRepository;
import ro.tuiasi.students.repositories.StudentRepository;
import ro.tuiasi.students.interfaces.StudentServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentService implements StudentServiceInterface
{
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AccommodationsRepository accommodationsRepository;

    @Override
    public Student createStudent(Student student, Integer id)
    {
        try
        {
            Student _student = new Student();
            _student.setId(id);
            _student.setFirstname(student.getFirstname());
            _student.setLastname(student.getLastname());
            _student.setPhone_number(student.getPhone_number());

            // Generare random nota
            _student.setYear_grade(new Random().nextFloat() * 10);

            return studentRepository.save(_student);
        }
        catch (Exception e)
        {
            throw e;
        }
    }

    @Override
    public List<Student> getAllStudents()
    {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Integer id)
    {
        return studentRepository.findById(id).get();
    }

    @Override
    public void deleteById(Integer id)
    {
        studentRepository.deleteById(id);
    }

    @Override
    public Setup getRoommates(Integer studentId) {
        Setup setup = new Setup();

        Student student = studentRepository.findById(studentId).orElse(null);
        if(student == null) {
            return null;
        }
        setup.setStudent(student);

        Room room = roomRepository.findById(student.getRooms_id()).orElse(null);
        if(room != null)
            setup.setRoom(room);
        setup.setAccommodation(accommodationsRepository.findById(room.getAccommodations_id()).orElse(null));

        String roommatesIdString = student.getRoommates();
        if(roommatesIdString == null || roommatesIdString.isEmpty())
            return setup;

        try {
            String[] ids = roommatesIdString.trim().split(",");
            ArrayList<Integer> processedIdList = new ArrayList<>();

            for (String id : ids) {
                processedIdList.add(Integer.parseInt(id.trim()));
            }

            List<Student> roommates = new ArrayList<>();
            processedIdList.forEach( id -> {
                if(studentRepository.findById(id).isPresent()) {
                    roommates.add(studentRepository.findById(id).get());
                }
            });

            setup.setRoommates(roommates);
            return setup;
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<Student> getAccommodatedStudents() {
        try {
            List<Student> students = studentRepository.getAccommodatedStudents();
            if(students == null) {
                return null;
            }

            return students;
        } catch(Exception e) {
            return null;
        }
    }
}
