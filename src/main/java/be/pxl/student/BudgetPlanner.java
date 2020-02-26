package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.util.BudgetPlannerImporter;

public class BudgetPlanner {
    public static void main(String[] args) {
<<<<<<< HEAD
        BudgetPlannerImporter importer = new BudgetPlannerImporter();
        Account account = importer.readFile("src\\main\\resources\\account_payments.csv");
=======
        BudgetPlannerImporter importer = new BudgetPlannerImporter("src\\main\\resources\\account_payments.csv");
        Account account = importer.readFile();
>>>>>>> b2ab2648b5dbc259d074c26c63d95a944fde9fd2
        System.out.println(account);
    }

}
