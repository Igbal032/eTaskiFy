package az.abb.etaskify.services;

import az.abb.etaskify.daos.interfaces.AccountDao;
import az.abb.etaskify.daos.interfaces.EmployeeDao;
import az.abb.etaskify.daos.interfaces.OrganizationDao;
import az.abb.etaskify.dtos.EmployeeDTO;
import az.abb.etaskify.enums.Roles;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Employee;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.services.interfaces.EmployeeService;
import az.abb.etaskify.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    private final ModelMapper modelMapper;
    private final AccountDao accountDao;
    private final OrganizationDao organizationDao;

    @Override
    public EmployeeDTO create(EmployeeDTO employeeDTO, Account company) {

        Account newAccount = modelMapper.map(employeeDTO, Account.class);
        accountDao.getAccountByEmail(newAccount.getEmail());
        Employee newEmployee = modelMapper.map(employeeDTO, Employee.class);
        Organization organization = organizationDao.getByEmail(company.getEmail());
        newEmployee.setOrganization(organization);
        organization.getEmployees().add(newEmployee);
        organizationDao.create(organization);
        newAccount = newAccount.toBuilder()
                .role(Roles.USER.toString())
                .passWord(Helper.passEncode(newAccount.getPassWord()))
                .build();
        accountDao.create(newAccount);
        return employeeDTO;
    }
}