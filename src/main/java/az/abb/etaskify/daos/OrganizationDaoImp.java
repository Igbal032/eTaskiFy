package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.OrganizationDao;
import az.abb.etaskify.models.Organization;
import az.abb.etaskify.repos.OrganizationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrganizationDaoImp implements OrganizationDao {
    private final OrganizationRepo organizationRepo;
    @Override
    public Organization save(Organization organization) {
        return organizationRepo.save(organization);
    }

    @Override
    public Organization getByEmail(String email) {
        return organizationRepo.getOrganizationByEmail(email
        );
    }
}
