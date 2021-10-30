package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.TaskDao;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.repos.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskDaoImp implements TaskDao {

    private final TaskRepo taskRepo;

    @Override
    public Task create(Task task) {
        return taskRepo.save(task);
    }
}
