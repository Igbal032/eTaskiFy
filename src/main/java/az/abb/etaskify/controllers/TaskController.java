package az.abb.etaskify.controllers;

import az.abb.etaskify.configs.JwtTokenUtil;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.repos.AccountRepo;
import az.abb.etaskify.services.interfaces.EmployeeService;
import az.abb.etaskify.services.interfaces.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

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
    public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO,HttpServletRequest request){
        Account account = jwtTokenUtil.getUserId(request.getHeader("Authorization"));
        return new ResponseEntity<>(taskService.save(taskDTO, account), HttpStatus.OK);
    }
    @PutMapping("/{taskId}/assign/{empId}")
    public ResponseEntity<?> assignTask(@PathVariable Long taskId, @PathVariable Long empId, HttpServletRequest request){
        Account account = jwtTokenUtil.getUserId(request.getHeader("Authorization"));
        taskService.assignTask(taskId, empId,account);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @PutMapping("/{taskId}/assigns")
    public ResponseEntity<?> assignTaskToMoreEmps(@PathVariable Long taskId, @RequestParam Long[] empIds, HttpServletRequest request){
        Account account = jwtTokenUtil.getUserId(request.getHeader("Authorization"));
        taskService.assignTaskToMoreEmployees(taskId, empIds,account);
        return new ResponseEntity<>("Saved",HttpStatus.OK);
    }

    @GetMapping("/ownTasks")
    public ResponseEntity<?> getOwnTasks(HttpServletRequest request){
        Account account = jwtTokenUtil.getUserId(request.getHeader("Authorization"));
        return new ResponseEntity<>(employeeService.getTasks(account.getEmail()),HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllTasks(HttpServletRequest request){
        Account account = jwtTokenUtil.getUserId(request.getHeader("Authorization"));
        return new ResponseEntity<>(taskService.getAllTaskOfOrganization(account),HttpStatus.OK);
    }
}
