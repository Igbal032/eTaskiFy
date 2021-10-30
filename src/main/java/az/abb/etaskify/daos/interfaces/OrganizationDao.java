package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Organization;

public interface OrganizationDao {
    Organization save(Organization organization);
    Organization getByEmail(String email);

}
