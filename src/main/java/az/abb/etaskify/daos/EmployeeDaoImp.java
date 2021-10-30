package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.EmployeeDao;
import az.abb.etaskify.exceptions.EmployeeNotFoundException;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeeDaoImp implements EmployeeDao {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee save(Employee employee) {
        return employeeRepo.save(employee);
    }

    @Override
    public Employee getEmployeeById(Long id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        if (employee.isEmpty())
            throw new EmployeeNotFoundException("Employee Not Found!!");
        return employee.get();
    }

    @Override
    public Employee getEmployeeByEmail(String email) {
        Employee employee = employeeRepo.getEmployeeByEmail(email);
        if (employee==null)
            throw new EmployeeNotFoundException("Employee not found!!");
        return employee;
    }
}
