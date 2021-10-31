package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.TaskDao;
import az.abb.etaskify.exceptions.TaskNotFoundException;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.repos.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskDaoImp implements TaskDao {

    private final TaskRepo taskRepo;

    @Override
    public Task save(Task task) {
        return taskRepo.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        Optional<Task> task = taskRepo.findById(id);
        if (task.isEmpty())
            throw new TaskNotFoundException("Task Not Found!!");
        return task.get();
    }

    @Override
    public List<Task> getAllTask() {
        List<Task> tasks = taskRepo.findAll();
        if (tasks.size()==0)
            throw new TaskNotFoundException("Task Not Found");
        return tasks;
    }
}
