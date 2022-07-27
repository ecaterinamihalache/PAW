package ro.tuiasi.uac.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuiasi.uac.interfaces.AccountServiceInterface;
import ro.tuiasi.uac.models.Account;
import ro.tuiasi.uac.models.Credentials;
import ro.tuiasi.uac.repositories.AccountRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements AccountServiceInterface {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public List<Account> getAccounts() {
        Iterable<Account> iterable = this.accountRepository.findAll();
        ArrayList<Account> accounts = new ArrayList<>();
        iterable.forEach(accounts::add);

        return accounts;
    }

    @Override
    public boolean addAccount(Account account) {
        try {
            this.accountRepository.save(account);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAccount(Account account) {
        try {
            this.accountRepository.delete(account);
            return true;
        } catch(Exception e) {
            return false;
        }
    }

    @Override
    public Account getAccountById(Integer id) {
        Optional<Account> account = this.accountRepository.findById(id);

        return account.orElse(null);
    }

    @Override
    public Optional<Account> getAccountByCredentials(String email, String password) {
        return this.accountRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public boolean deleteAccountById(Integer id) {
        Account accountToBeDeleted = this.accountRepository.findById(id).orElse(null);

        if(accountToBeDeleted != null) {
            this.accountRepository.delete(accountToBeDeleted);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Account updateAccount(Account account) {
        Account accountToBeUpdated = this.accountRepository.findById(account.getId()).orElse(null);

        if(accountToBeUpdated != null) {
            try {
                this.accountRepository.save(account);
                return account;
            } catch(Exception e) {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public Account updateAccount(Integer id, HashMap<String, Object> propertiesToUpdate) {
        try {
            Account accountToBeUpdated = this.accountRepository.findById(id).orElse(null);

            if(accountToBeUpdated != null) {
                String email = (String)propertiesToUpdate.get("email");
                String password = (String)propertiesToUpdate.get("password");
                Boolean isActive = (Boolean)propertiesToUpdate.get("isActive");
                String role = (String)propertiesToUpdate.get("role");

                Account updatedAccount = new Account(
                        accountToBeUpdated.getId(),
                        accountToBeUpdated.getEmail(),
                        accountToBeUpdated.getPassword(),
                        accountToBeUpdated.getIsActive(),
                        accountToBeUpdated.getRole());
                if(email != null)
                    updatedAccount.setEmail(email);
                if(password != null)
                    updatedAccount.setPassword(password);
                if(isActive != null)
                    updatedAccount.setIsActive(isActive);
                if(role != null)
                    updatedAccount.setRole(role);

                this.accountRepository.save(updatedAccount);

                return updatedAccount;
            }
            return null;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
