package ro.tuiasi.uac.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="Accounts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String email;
    private String password;
    private Boolean isActive;
    private String role;
}
