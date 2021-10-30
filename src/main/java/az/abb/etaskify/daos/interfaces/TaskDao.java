package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Task;

public interface TaskDao {
    Task save(Task task);
    Task getTaskById(Long id);
}
