package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Preferences")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Preference
{
    @Id
    private Integer id;
    private String rooms;
    private String roommates;
}
