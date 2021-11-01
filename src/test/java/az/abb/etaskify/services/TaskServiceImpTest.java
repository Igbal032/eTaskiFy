package az.abb.etaskify.services;

import az.abb.etaskify.daos.interfaces.EmployeeDao;
import az.abb.etaskify.daos.interfaces.OrganizationDao;
import az.abb.etaskify.daos.interfaces.TaskDao;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.models.Task;
import az.abb.etaskify.repos.EmployeeRepo;
import az.abb.etaskify.repos.OrganizationRepo;
import az.abb.etaskify.repos.TaskRepo;
import az.abb.etaskify.services.interfaces.TaskService;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TaskServiceImpTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private TaskRepo taskRepo;
    @Autowired
    private TaskService taskService;

    @Autowired
    private OrganizationDao organizationDao;
    @Autowired
    private OrganizationRepo organizationRepo;


    @Test
    @Order(1)
    @DisplayName("TaskService -> save Service")
    void save() {
        //given
        Employee employee = Employee.builder()
                .email("test@mail.ru")
                .name("testName")
                .surname("testSurname")
                .build();
        Employee saveEmp = employeeDao.save(employee);

        //when
        Task task = Task.builder()
                .buildByEmployee(saveEmp)
                .title("Test Title")
                .description("Test Description")
                .deadline(LocalDateTime.now())
                .status("TO_DO")
                .build();
        TaskDTO savedTask = taskService.save(modelMapper.map(task, TaskDTO.class), Account.builder().email(saveEmp.getEmail()).build());
        System.out.println(savedTask.getId()+" test 1");
        System.out.println(savedTask.getId()+" test 2");
        //Then
        assertEquals(savedTask.getTitle(), "Test Title");
        assertEquals(savedTask.getStatus(), "TO_DO");
        assertEquals(taskRepo.findAll().size(), 4);
    }

    @BeforeAll
    public void init() {
        List<Employee> emps = new ArrayList<>();
        Organization organization = Organization.builder().email("testOrg@mail.ru").organizationName("Test Organization").build();
        Organization saveOrganization = organizationDao.save(organization);
        Employee employee = Employee.builder().email("test1@mail.ru").name("testName").surname("testSurname").organization(saveOrganization).build();
        Employee employee2 = Employee.builder().email("test2@mail.ru").name("testName2").surname("testSurname2").organization(saveOrganization).build();
        Employee employee3 = Employee.builder().email("test3@mail.ru").name("testName3").surname("testSurname3").organization(saveOrganization).build();
        Employee employee4 = Employee.builder().email("test4@mail.ru").name("testName4").surname("testSurname4").organization(saveOrganization).build();
        emps.add(employee);
        emps.add(employee2);
        emps.add(employee3);
        employeeRepo.saveAll(emps);
        Employee employee1 =  employeeRepo.save(employee4);
        List<Task> tasks = new ArrayList<>();
        Task task = Task.builder().buildByEmployee(employee).title("Test Title").description("Test Description").deadline(LocalDateTime.now()).status("TO_DO").build();
        Task task2 = Task.builder().buildByEmployee(employee2).title("Test Title").description("Test Description").deadline(LocalDateTime.now()).status("TO_DO").build();
        Task task3 = Task.builder().buildByEmployee(employee3).title("Test Title").description("Test Description").deadline(LocalDateTime.now()).status("TO_DO").build();
        task = taskRepo.save(task);
        task2 = taskRepo.save(task2);
        task3 = taskRepo.save(task3);

    }


    @AfterAll
    public void clean() {
        taskRepo.deleteAll();
        employeeRepo.deleteAll();
        organizationRepo.deleteAll();
    }
}