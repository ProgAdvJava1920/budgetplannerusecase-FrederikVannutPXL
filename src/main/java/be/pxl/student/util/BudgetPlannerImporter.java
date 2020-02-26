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
    private Account account;

    public BudgetPlannerImporter() {
    }

    public Account readFile(String _filename) {
        List<Payment> paymentList = new ArrayList<>();
        Path path = Paths.get(_filename);
        try {
            BufferedReader reader = Files.newBufferedReader((path));
            String line = reader.readLine();
            line = reader.readLine();
            while (line != null) {
                String splittedstring[] = line.split(",");
                if(account == null){
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
        Payment payment = new Payment(LocalDateTime.parse(line[3], formatter), Float.parseFloat(line[4]), line[5], line[6]);
        return payment;
    }
}