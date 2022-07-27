package ro.tuiasi.accommodations.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Accommodation")
@Table(name="Accommodations")
public class Accommodation
{
    @Id
    private Integer id;
    private String name;
    private Integer room_capacity;

    public Accommodation()
    {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRoom_capacity() {
        return room_capacity;
    }

    public void setRoom_capacity(Integer room_capacity) {
        this.room_capacity = room_capacity;
    }
}
