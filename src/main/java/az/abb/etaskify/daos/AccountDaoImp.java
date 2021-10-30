package az.abb.etaskify.daos;

import az.abb.etaskify.daos.interfaces.AccountDao;
import az.abb.etaskify.exceptions.AccountExistsException;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.repos.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountDaoImp implements AccountDao {

    private final AccountRepo accountRepo;

    @Override
    public Account getAccountByEmail(String email) {
        Account account = accountRepo.findAccountByEmail(email);
        if (account!=null)
            throw new AccountExistsException("Account Exists");
        return null;
    }

    @Override
    public Account create(Account account) {
        return accountRepo.save(account);
    }
}
