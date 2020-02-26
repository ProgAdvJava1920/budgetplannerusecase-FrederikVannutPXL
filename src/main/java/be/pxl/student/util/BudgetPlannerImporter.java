package be.pxl.student.util;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Util class to import csv file
 */
public class BudgetPlannerImporter {
<<<<<<< HEAD
    private Account account;

    public BudgetPlannerImporter() {
    }

    public Account readFile(String filename) {
        List<Payment> paymentList = new ArrayList<>();
        Path path = Paths.get(filename);
=======
    private String _filename;
    private Account account;

    public BudgetPlannerImporter(String filename) {
        this._filename = filename;
    }

    public Account readFile() {
        List<Payment> paymentList = new ArrayList<>();
        Path path = Paths.get(_filename);
>>>>>>> b2ab2648b5dbc259d074c26c63d95a944fde9fd2
        try {
            BufferedReader reader = Files.newBufferedReader((path));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String splittedstring[] = line.split(",");
<<<<<<< HEAD
                if (account == null) {
=======
                if(account == null){
>>>>>>> b2ab2648b5dbc259d074c26c63d95a944fde9fd2
                    account = createAccount(splittedstring);
                }
                Payment payment = CreatePayment(splittedstring);
                paymentList.add(payment);
                line = reader.readLine();
            }
            account.setPayments(paymentList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return account;
    }

    private Account createAccount(String[] line) {
        Account account = new Account();
        account.setIBAN(line[1]);
        account.setName(line[0]);
        return account;
    }

    private Payment CreatePayment(String[] line) {
        //Account name,Account IBAN,Counteraccount IBAN,Transaction date,Amount,Currency,Detail
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
<<<<<<< HEAD
        Payment payment = new Payment(LocalDateTime.parse(line[3], formatter), Float.parseFloat(line[4]), line[5], line[6]);
=======
        Payment payment = new Payment(LocalDateTime.parse(line[3],formatter),Float.parseFloat(line[4]),line[5],line[6]);
>>>>>>> b2ab2648b5dbc259d074c26c63d95a944fde9fd2
        return payment;
    }
}
