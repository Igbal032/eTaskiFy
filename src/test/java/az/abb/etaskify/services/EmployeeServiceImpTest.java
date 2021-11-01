package az.abb.etaskify.services;

import az.abb.etaskify.dtos.EmployeeDTO;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.repos.EmployeeRepo;
import az.abb.etaskify.repos.OrganizationRepo;
import az.abb.etaskify.repos.TaskRepo;
import az.abb.etaskify.services.interfaces.EmployeeService;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmployeeServiceImpTest {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private OrganizationRepo organizationRepo;

    @Test
    @Order(1)
    @DisplayName("save -> save Employee")
    void save() {
        EmployeeDTO employeeDTO = EmployeeDTO.builder().email("test32@box.az").name("Test Name").passWord("123456789").surname("Test Surname").build();
        employeeService.save(employeeDTO, Account.builder().email("testOrg@mail.ru").build());
        assertEquals(employeeRepo.getEmployeeByEmail("test32@box.az").getName(),employeeDTO.getName());
        assertEquals(employeeRepo.getEmployeeByEmail("test32@box.az").getSurname(),employeeDTO.getSurname());
        assertEquals(employeeRepo.getEmployeeByEmail("test32@box.az").getEmail(),employeeDTO.getEmail());
    }

    @Test
    @Order(2)
    @DisplayName("getTasks -> get Tasks")
    void getTasks() {
        List<TaskDTO> tasks = employeeService.getTasks("empMail@box.az");
        assertEquals(employeeRepo.getEmployeeByEmail("empMail@box.az").getTasks().size(), tasks.size());
    }

    @BeforeAll
    public void init() {
        Organization organization = Organization.builder().email("testOrg@mail.ru").organizationName("Test Organization").employees(Arrays.asList()).build();
        organizationRepo.save(organization);
        Task task1 = Task.builder().title("Title").description("Description").deadline(LocalDateTime.now()).build();
        Task task2 = Task.builder().title("Title2").description("Description2").deadline(LocalDateTime.now()).build();
        taskRepo.save(task1);
        taskRepo.save(task2);
        Employee employee = Employee.builder().email("empMail@box.az").name("Name Emp").surname("Surname Emp").build();
        employee.setTasks(Arrays.asList(task1, task2));
        employeeRepo.save(employee);
        Employee employee1 = employeeRepo.getEmployeeByEmail("empMail@box.az");

    }

    @AfterAll
    public void clean() {
        employeeRepo.deleteAll();
        taskRepo.deleteAll();
        organizationRepo.deleteAll();
    }

}