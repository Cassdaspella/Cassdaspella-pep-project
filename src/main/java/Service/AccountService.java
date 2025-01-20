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

    public Account addAccount(Account account){
        return accountDAO.registerAccount(account);
    }
    
    public Account checkAccount(Account account){
        if(account.getUsername() == "" || account.getUsername() == null){
            return null;
        }
        if(account.getPassword() == null || account.getPassword().length() < 4){
            return null;
        }
        
        return null;
    }
}
