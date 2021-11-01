package az.abb.etaskify.services;

import az.abb.etaskify.daos.interfaces.EmployeeDao;
import az.abb.etaskify.daos.interfaces.TaskDao;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.exceptions.TaskNotFoundException;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Mail;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.services.interfaces.TaskService;
import az.abb.etaskify.utils.SendMailService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImp implements TaskService {

    private final ModelMapper modelMapper;
    private final TaskDao taskDao;
    private final EmployeeDao employeeDao;
    private final SendMailService sendMailService;

    @Override
    public TaskDTO save(TaskDTO taskDTO, Account account) {
        Task newTask = modelMapper.map(taskDTO, Task.class);
        newTask.setBuildByEmployee(employeeDao.getEmployeeByEmail(account.getEmail()));
        return modelMapper.map(taskDao.save(newTask),TaskDTO.class);
    }
    @Override
    public void assignTask(Long taskId, Long empId,Account account) {
        Task assignedTask = taskDao.getTaskById(taskId);
        Employee checkEmp = employeeDao.getEmployeeByEmail(account.getEmail());
        Employee selectedEmployee = employeeDao.getEmployeeById(empId);
        if (checkEmp.equals(assignedTask.getBuildByEmployee())&&checkEmp.getOrganization().equals(selectedEmployee.getOrganization())) {
            assignedTask.getEmployees().add(selectedEmployee);
            taskDao.save(assignedTask);
            sendMailService.sendMail(new Mail(selectedEmployee.getEmail(),"New Task",assignedTask.getDescription()));
        }
        else{
            throw new TaskNotFoundException("This task doesnt belong to this employee");
        }
    }
    @Override
    public void assignTaskToMoreEmployees(Long taskId, Long[] empIds,Account account) {
        Task assignedTask = taskDao.getTaskById(taskId);
        Employee checkEmp = employeeDao.getEmployeeByEmail(account.getEmail());
        if (checkEmp.equals(assignedTask.getBuildByEmployee())){
            for (int i = 0; i < empIds.length; i++) {
                Employee selectedEmployee = employeeDao.getEmployeeById(empIds[i]);
                if (checkEmp.getOrganization().equals(selectedEmployee.getOrganization())){
                    assignedTask.getEmployees().add(selectedEmployee);
                    taskDao.save(assignedTask);
                    System.out.println(selectedEmployee.getEmail());
                    sendMailService.sendMail(new Mail(selectedEmployee.getEmail(),"New Task",assignedTask.getDescription()));
                }
                else {
                    throw new TaskNotFoundException("This task doesnt belong to this employee");
                }
            }
        }
        else{
            throw new TaskNotFoundException("This task doesnt belong to this employee");
        }
    }

    @Override
    public List<TaskDTO> getAllTaskOfOrganization(Account account) {
        Employee employee = employeeDao.getEmployeeByEmail(account.getEmail());
        TypeToken<List<TaskDTO>> typeToken = new TypeToken<>() {
        };
        List<Task> tasks = taskDao.getAllTask();
        List<Task> filtered = tasks.stream()
                .filter(t->t.getBuildByEmployee()
                        .getOrganization().getId()==employee.getOrganization().getId())
                .collect(Collectors.toList());
        return modelMapper.map(filtered,typeToken.getType());
    }
}
