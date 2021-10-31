package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.EmployeeDTO;
import az.abb.etaskify.dtos.RegisterDTO;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO, Account company);
    List<TaskDTO> getTasks(String email);

}
