package az.abb.etaskify.services;

import az.abb.etaskify.daos.interfaces.EmployeeDao;
import az.abb.etaskify.daos.interfaces.OrganizationDao;
import az.abb.etaskify.daos.interfaces.TaskDao;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private final ModelMapper modelMapper;
    private final OrganizationDao organizationDao;
    private final TaskDao taskDao;
    private final EmployeeDao employeeDao;

    @Override
    public TaskDTO save(TaskDTO taskDTO, Account company) {
        Task newTask = modelMapper.map(taskDTO, Task.class);
        newTask.setOrganization(organizationDao.getByEmail(company.getEmail()));
        taskDao.save(newTask);
        return taskDTO;
    }

    @Override
    public void assignTask(Long taskId, Long empId) {
        Task assignedTask = taskDao.getTaskById(taskId);
        Employee employee = employeeDao.getEmployeeById(empId);
        assignedTask.getEmployees().add(employee);
        Task saved = taskDao.save(assignedTask);
    }


}
