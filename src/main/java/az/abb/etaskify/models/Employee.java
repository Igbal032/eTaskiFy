package az.abb.etaskify.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;
    private String surname;
    private String email;
    @ManyToMany
    @JoinTable(
            name = "assigned_tasks",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    private List<Task> tasks;
    @ManyToOne
    private Organization organization;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
}
