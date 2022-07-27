package ro.tuiasi.accommodations.models;

import javax.persistence.*;

@Entity(name = "Room")
@Table(name="Rooms")
public class Room
{
    @Id
    private Integer id;
    private String room;
    private Integer floor;
    private String side;
    private Integer accommodations_id;

    public Room()
    {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public Integer getAccommodations_id() {
        return accommodations_id;
    }

    public void setAccommodations_id(Integer accommodations_id) {
        this.accommodations_id = accommodations_id;
    }
}
