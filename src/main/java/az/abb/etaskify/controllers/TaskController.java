package az.abb.etaskify.controllers;

import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.repos.AccountRepo;
import az.abb.etaskify.repos.OrganizationRepo;
import az.abb.etaskify.services.interfaces.EmployeeService;
import az.abb.etaskify.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/tasks")
public class TaskController {

    private final TaskService taskService;
    private final AccountRepo accountRepo;
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO){
        Account account = accountRepo.findAccountByEmail("iqbal.hoff32@list.ru");
        return new ResponseEntity<>(taskService.save(taskDTO, account), HttpStatus.OK);
    }
    @PutMapping("/{taskId}/assign/{empId}")
    public ResponseEntity<?> assignTask(@PathVariable Long taskId, @PathVariable Long empId){
        taskService.assignTask(taskId, empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTasks(){
        Account account = accountRepo.findAccountByEmail("ihasanli32@hotmail.com");
        return new ResponseEntity<>(employeeService.getTask(account.getEmail()),HttpStatus.OK);
    }
}
