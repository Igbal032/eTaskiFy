package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Task;

import java.util.List;

public interface TaskDao {
    Task save(Task task);
    Task getTaskById(Long id);
    List<Task> getAllTask();
}
