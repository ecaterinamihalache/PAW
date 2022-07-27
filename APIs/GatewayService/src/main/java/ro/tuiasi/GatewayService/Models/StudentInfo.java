package ro.tuiasi.GatewayService.Models;

import java.io.Serializable;

public class StudentInfo implements Serializable {
    private Integer id;
    private String university;
    private String faculty;
    private String specialization;

    public StudentInfo() {}

    public Integer getId() {
        return id;
    }

    public String getUniversity() {
        return university;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}
