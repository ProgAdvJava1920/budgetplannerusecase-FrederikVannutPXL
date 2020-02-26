package be.pxl.student;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import be.pxl.student.util.BudgetPlannerImporter;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetPlannerImporterTest {
    BudgetPlannerImporter importer = new BudgetPlannerImporter();
    Account testaccount = importer.readFile("src\\main\\resources\\account_payments.csv");
    List<Payment> paymentList = testaccount.getPayments();

    @Test
    public void AccountnameTest() {
        //76   Jos,BE69771770897312,BE26372611819430,Thu Feb 06 04:04:28 CET 2020,-470.64,EUR,Sit eaque suscipit.
        assertEquals("Jos", testaccount.getName());
    }

    @Test
    public void AccountIBanTest() {
        assertEquals("BE69771770897312", testaccount.getIBAN());
    }

    @Test
    public void PaymentCurrencyTest() {
        // index 74 omdat eerst regel niet meetelt en zero based.
        assertEquals("EUR", paymentList.get(74).getCurrency());
    }

    @Test
    public void PaymentDetailTest() {
        assertEquals("Sit eaque suscipit.", paymentList.get(74).getDetail());
    }

    @Test
    public void PaymentAmountTest() {
        assertEquals(-470.6400146484375, paymentList.get(74).getAmount());
    }

    @Test
    public void PaymentDateTest() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
        assertEquals(LocalDateTime.parse("Thu Feb 06 04:04:28 CET 2020", formatter), paymentList.get(74).getDate());

    }
}
