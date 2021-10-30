package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Organization;

public interface OrganizationDao {
    Organization create(Organization organization);
    Organization getByEmail(String email);

}
