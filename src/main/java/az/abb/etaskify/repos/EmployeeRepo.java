package az.abb.etaskify.repos;

import az.abb.etaskify.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Long> {
    Employee getEmployeeByEmail(String email);
}
