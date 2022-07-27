package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Students")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Student
{
    @Id
    private Integer id;
    private String firstname;
    private String lastname;
    private String phone_number;
    private Float year_grade;
    private Integer rooms_id;
    private String roommates;
}
