package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Employee;

public interface EmployeeDao {
    Employee save(Employee employee);
    Employee getEmployeeById(Long id);
    Employee getEmployeeByEmail(String email);
}
