package az.abb.etaskify.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String phoneNumber;
    private String address;
    @OneToMany
    private List<Employee> employeeList;
    @OneToOne
    private Owner owner;
}
