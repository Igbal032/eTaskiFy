package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.OwnerDao;
import az.abb.etaskify.models.Owner;
import az.abb.etaskify.repos.OwnerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OwnerDaoImp implements OwnerDao {

    private final OwnerRepo ownerRepo;

    @Override
    public Owner save(Owner owner) {
        return ownerRepo.save(owner);
    }
}
