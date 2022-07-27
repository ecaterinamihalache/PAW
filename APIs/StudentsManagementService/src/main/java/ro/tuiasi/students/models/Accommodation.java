package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Accommodations")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Accommodation
{
    @Id
    private Integer id;
    private String name;
    private Integer room_capacity;
}
