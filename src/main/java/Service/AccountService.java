package Service;

import DAO.AccountDAO;
import Model.Account;

public class AccountService {
    AccountDAO accountDAO;

    public AccountService(){
        accountDAO = new AccountDAO();
    }

    public AccountService(AccountDAO accountDAO){
        this.accountDAO = accountDAO;
    }
/**
 * o : Make sure the Username is NOT blank
 * o : Make sure the password is at least 4 char long
 * x : Make sure the User name does NOT already exist
 * @param account
 * @return
 */
    public Account addAccount(Account account){
        if(account.getUsername() == "" || account.getUsername() == null){
            return null;
        }
        if (account.getPassword() == null || account.getPassword().length() < 4){
            return null;
        }
        else {
            return accountDAO.registerAccount(account);
        }
    }
    /**
     * x : Make sure that the username exists
     * x : Make sure that the password exists
     * @param username
     * @param password
     * @return
     */
    public Account getLogIn(String username, String password){
        if(username == null){
            return null;
        }
        return null;
    }
}
