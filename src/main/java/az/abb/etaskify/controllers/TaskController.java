package az.abb.etaskify.controllers;

import az.abb.etaskify.dtos.RegisterDTO;
import az.abb.etaskify.dtos.TaskDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.repos.AccountRepo;
import az.abb.etaskify.repos.OrganizationRepo;
import az.abb.etaskify.services.interfaces.OrganizationService;
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
    private final OrganizationRepo organizationRepo;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TaskDTO taskDTO){
        Account account = accountRepo.findAccountByEmail("iqbal.hoff32@list.ru");
        return new ResponseEntity<>(taskService.create(taskDTO, account), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<?> geEmps(){
        Account account = accountRepo.findAccountByEmail("iqbal.hoff32@list.ru");
        Organization ss = organizationRepo.getOrganizationByEmail(account.getEmail());
        List<Employee> sss = ss.getEmployees();
        System.out.println(sss.size()+ " -size");
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
