package az.abb.etaskify.controllers;

import az.abb.etaskify.dtos.RegisterDTO;
import az.abb.etaskify.services.interfaces.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organization")
public class OrganizationController {

    private final OrganizationService organizationService;

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody  RegisterDTO registerDTO){
        RegisterDTO registerDTO1 = organizationService.create(registerDTO);
        return new ResponseEntity<>("Hello World!!",HttpStatus.OK);
    }
}
