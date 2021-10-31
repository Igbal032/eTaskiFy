package az.abb.etaskify.services;

import java.util.ArrayList;

import az.abb.etaskify.exceptions.AccountNotFoundException;
import az.abb.etaskify.models.Account;
import az.abb.etaskify.repos.AccountRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final AccountRepo accountRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepo.findAccountByEmail(email);
        if (account!=null) {
            return new User(account.getEmail(),account.getPassWord(),
                    new ArrayList<>());
        } else {
            throw new AccountNotFoundException("User not found with username: " + account.getEmail());
        }
    }
}
