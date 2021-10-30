package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.EmployeeDTO;
import az.abb.etaskify.dtos.RegisterDTO;
import az.abb.etaskify.models.Account;

public interface EmployeeService {
    EmployeeDTO create(EmployeeDTO employeeDTO, Account company);

}
