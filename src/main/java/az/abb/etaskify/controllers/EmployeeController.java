package az.abb.etaskify.controllers;

import az.abb.etaskify.dtos.EmployeeDTO;
import az.abb.etaskify.dtos.RegisterDTO;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.repos.AccountRepo;
import az.abb.etaskify.services.interfaces.EmployeeService;
import az.abb.etaskify.services.interfaces.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final AccountRepo accountRepo;
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody EmployeeDTO employeeDTO){
        Account account = accountRepo.findAccountByEmail("iqbal.hoff32@list.ru");
        return new ResponseEntity<>(employeeService.create(employeeDTO, account), HttpStatus.OK);
    }

}
