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
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long Id;
    private String title;
    private String description;
    private LocalDateTime deadline;
    private String status;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "assigned_tasks",
            joinColumns = @JoinColumn(name = "task_id"),
            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employees;
    @ManyToOne
    private Employee buildByEmployee;
    @CreationTimestamp
    private LocalDateTime createdDate;
    private LocalDateTime deletedDate;
}
