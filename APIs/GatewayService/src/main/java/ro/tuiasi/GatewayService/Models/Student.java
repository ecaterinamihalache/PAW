package ro.tuiasi.GatewayService.Models;

import java.io.Serializable;

public class Student implements Serializable {
    private Integer id;
    private String firstname;
    private String lastname;
    private String phone_number;
    private Float year_grade;
    private Integer rooms_id;

    public Student() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public Float getYear_grade() {
        return year_grade;
    }

    public Integer getRooms_id() {
        return rooms_id;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setYear_grade(Float year_grade) {
        this.year_grade = year_grade;
    }

    public void setRooms_id(Integer rooms_id) {
        this.rooms_id = rooms_id;
    }
}
