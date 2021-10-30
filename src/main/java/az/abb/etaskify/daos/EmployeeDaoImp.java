package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.EmployeeDao;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.repos.EmployeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeDaoImp implements EmployeeDao {

    private final EmployeeRepo employeeRepo;

    @Override
    public Employee create(Employee employee) {
        return employeeRepo.save(employee);
    }
}
