package az.abb.etaskify.repos;

import az.abb.etaskify.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepo extends JpaRepository<Owner, Long> {
}
