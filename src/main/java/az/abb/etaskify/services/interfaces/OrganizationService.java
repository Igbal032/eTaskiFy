package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.RegisterDTO;

public interface OrganizationService {
    RegisterDTO create(RegisterDTO registerDTO);
}
