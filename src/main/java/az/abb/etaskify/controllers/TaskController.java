package az.abb.etaskify.controllers;

import az.abb.etaskify.configs.JwtTokenUtil;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.repos.AccountRepo;
import az.abb.etaskify.repos.OrganizationRepo;
import az.abb.etaskify.services.interfaces.EmployeeService;
import az.abb.etaskify.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/tasks")
public class TaskController {

    private final TaskService taskService;
    private final AccountRepo accountRepo;
    private final EmployeeService employeeService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO){
        System.out.println("Into CREATE");
        Account account = accountRepo.findAccountByEmail("iqbal.hoff32@list.ru");
        return new ResponseEntity<>(taskService.save(taskDTO, account), HttpStatus.OK);
    }
    @PutMapping("/{taskId}/assign/{empId}")
    public ResponseEntity<?> assignTask(@PathVariable Long taskId, @PathVariable Long empId, HttpServletRequest request){
        Account account = jwtTokenUtil.getUserId(request.getHeader("Authorization"));

        taskService.assignTask(taskId, empId);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @PutMapping("/{taskId}/assigns")
    public ResponseEntity<?> assignTaskToMoreEmps(@PathVariable Long taskId, @RequestParam Long[] empIds){
        taskService.assignTaskToMoreEmployees(taskId, empIds);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getTasks(HttpServletRequest request){
        Account accountt = jwtTokenUtil.getUserId(request.getHeader("Authorization"));
        System.out.println(accountt.getEmail());
        Account account = accountRepo.findAccountByEmail("ihasanli323232@hotmail.com");
        return new ResponseEntity<>(employeeService.getTask(account.getEmail()),HttpStatus.OK);
    }
}
