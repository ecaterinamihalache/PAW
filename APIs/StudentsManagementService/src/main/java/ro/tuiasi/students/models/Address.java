package ro.tuiasi.students.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Addresses")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Address
{
    @Id
    private Integer id;
    private String city;
    private String county;
    private String street_name;
    private String country;
    private String postal_code;
}
