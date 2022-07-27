package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Setup {
    private Student student;
    private List<Student> roommates;
    private Room room;
    private Accommodation accommodation;
}
