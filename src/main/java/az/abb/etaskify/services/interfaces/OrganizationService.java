package az.abb.etaskify.services.interfaces;

import az.abb.etaskify.dtos.RegisterDTO;

public interface OrganizationService {
    RegisterDTO save(RegisterDTO registerDTO);
}
