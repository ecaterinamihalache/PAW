package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Student_details")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class StudentInfo
{
    @Id
    private Integer id;
    private String university;
    private String faculty;
    private String specialization;
}
