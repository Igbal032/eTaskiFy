package az.abb.etaskify.repos;

import az.abb.etaskify.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {

}
