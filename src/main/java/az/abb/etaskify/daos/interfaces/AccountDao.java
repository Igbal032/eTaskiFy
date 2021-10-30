package az.abb.etaskify.daos.interfaces;

import az.abb.etaskify.models.Account;

public interface AccountDao {
    Account getAccountByEmail(String email);
    Account save(Account account);
}
