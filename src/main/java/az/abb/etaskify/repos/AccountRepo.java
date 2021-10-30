package az.abb.etaskify.repos;

import az.abb.etaskify.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
    Account findAccountByEmail(String email);
}
