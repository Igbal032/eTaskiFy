package az.abb.etaskify.services;

import az.abb.etaskify.daos.interfaces.AccountDao;
import az.abb.etaskify.daos.interfaces.OrganizationDao;
import az.abb.etaskify.dtos.RegisterDTO;
import az.abb.etaskify.enums.Roles;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.models.Owner;
import az.abb.etaskify.services.interfaces.OrganizationService;
import az.abb.etaskify.utils.Helper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImp implements OrganizationService {

    private final ModelMapper modelMapper;
    private final OrganizationDao organizationDao;
    private final AccountDao accountDao;

    @Override
    public RegisterDTO create(RegisterDTO registerDTO) {
        Account newAccount = modelMapper.map(registerDTO,Account.class);
        accountDao.getAccountByEmail(newAccount.getEmail());
        Owner newOwner = modelMapper.map(registerDTO,Owner.class);
        Organization newOrg = modelMapper.map(registerDTO,Organization.class);
        newOrg.setOwner(newOwner);
        organizationDao.create(newOrg);
        accountDao.create(newAccount.toBuilder()
                .passWord(Helper.passEncode(newAccount.getPassWord()))
                .role(Roles.ADMIN.toString())
                .build());
        return registerDTO;
    }
}
