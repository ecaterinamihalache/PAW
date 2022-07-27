package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Rooms")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Room
{
    @Id
    private Integer id;
    private String room;
    private String floor;
    private String side;
    private Integer accommodations_id;
}
