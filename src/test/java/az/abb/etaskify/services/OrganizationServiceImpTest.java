package az.abb.etaskify.services;

import az.abb.etaskify.models.Organization;
import az.abb.etaskify.repos.OrganizationRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OrganizationServiceImpTest {


    @Autowired
    private OrganizationRepo organizationRepo;

    @Test
    @Order(1)
    @DisplayName("save -> save Organization")
    void save() {
        Organization organization = Organization.builder().email("testOrg@mail.ru").organizationName("Test Organization").build();
        Organization saveOrganization = organizationRepo.save(organization);
        assertEquals(organizationRepo.getOrganizationByEmail(organization.getEmail()).getEmail(),saveOrganization.getEmail());
    }

    @AfterAll
    public void clean() {
        organizationRepo.deleteAll();
    }
}