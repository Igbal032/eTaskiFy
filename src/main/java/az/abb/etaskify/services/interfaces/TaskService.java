package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Task;

public interface TaskService {
    TaskDTO create(TaskDTO taskDTO, Account company);
}
