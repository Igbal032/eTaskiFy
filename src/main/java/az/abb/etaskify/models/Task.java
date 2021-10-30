package az.abb.etaskify.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String description;
    private String deadline;
    private String status;
    @ManyToMany(mappedBy = "tasks")
    private List<Employee> employeeList;
    @ManyToOne
    private Organization organization;
}
