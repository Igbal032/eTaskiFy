package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;

public interface TaskService {
    TaskDTO save(TaskDTO taskDTO, Account company);
    void assignTask(Long taskId, Long empId);
}
