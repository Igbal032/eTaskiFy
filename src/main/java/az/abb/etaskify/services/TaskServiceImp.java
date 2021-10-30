package az.abb.etaskify.services;

import az.abb.etaskify.daos.interfaces.OrganizationDao;
import az.abb.etaskify.daos.interfaces.TaskDao;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private final ModelMapper modelMapper;
    private final OrganizationDao organizationDao;
    private final TaskDao taskDao;

    @Override
    public TaskDTO create(TaskDTO taskDTO, Account company) {
        Task newTask = modelMapper.map(taskDTO, Task.class);
        newTask.setOrganization(organizationDao.getByEmail(company.getEmail()));
        taskDao.create(newTask);
        return taskDTO;
    }
}
