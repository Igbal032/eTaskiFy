package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;

import java.util.List;

public interface TaskService {
    TaskDTO save(TaskDTO taskDTO, Account account);
    void assignTask(Long taskId, Long empId,Account account);
    void assignTaskToMoreEmployees(Long taskId, Long[] empIds,Account account);
    List<TaskDTO> getAllTaskOfOrganization(Account account);
}
