package ro.tuiasi.GatewayService.Models;

import java.io.Serializable;

public class Preference implements Serializable
{
    private Integer id;
    private String rooms;
    private String roommates;
    private String accommodations;

    public Preference() {}

    public Preference(Integer id, String rooms, String roommates, String accommodations) {
        this.id = id;
        this.rooms = rooms;
        this.roommates = roommates;
        this.accommodations = accommodations;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getRoommates() {
        return roommates;
    }

    public void setRoommates(String roommates) {
        this.roommates = roommates;
    }

    public String getAccommodations() {
        return accommodations;
    }

    public void setAccommodations(String accommodations) {
        this.accommodations = accommodations;
    }
}
