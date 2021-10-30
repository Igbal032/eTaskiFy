package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Task;

public interface TaskDao {
    Task create(Task task);
}
