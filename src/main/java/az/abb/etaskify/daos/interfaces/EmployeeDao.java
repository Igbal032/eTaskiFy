package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Employee;

public interface EmployeeDao {
    Employee create(Employee employee);
}
