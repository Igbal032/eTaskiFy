package az.abb.etaskify.repos;

import az.abb.etaskify.models.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrganizationRepo extends JpaRepository<Organization,Long> {
    Organization getOrganizationByEmail(String email);
}
