package be.pxl.student;

import be.pxl.student.doa.AccountDAO;
import be.pxl.student.entity.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class AccountDAOTest {
    @Test
    public void testAccountInsert() {
        Account account = new Account();
        account.setIBAN("testIBAN");
        account.setName("testName");

        AccountDAO accountDAO = new AccountDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");
        Account accountInserted = accountDAO.createAccount(account);
        //assertEquals(account,accountInserted);
        assertNotEquals(0, accountInserted.getId());
        System.out.println(accountInserted.getId());
    }
}
