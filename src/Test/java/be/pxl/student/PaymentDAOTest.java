package be.pxl.student;

import be.pxl.student.doa.PaymentDAO;
import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static be.pxl.student.doa.PaymentDAO.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PaymentDAOTest {
    @Test
    public void testPaymentInsert() {
        Payment payment = new Payment();
        payment.setCurrency("EUR");
        payment.setId(1);
        payment.setLabelId(1);
        payment.setCounterAccountId(1);
        payment.setAccountId(1);
        payment.setAmount(200);
        payment.setDate(LocalDateTime.now());
        payment.setDetail("test value");

        PaymentDAO paymentDAO = new PaymentDAO("jdbc:mysql://localhost:3306/budgetplanner?useSSL=false", "root", "admin");
        Payment paymentInserted = paymentDAO.createPayment(payment);
        //assertEquals(account,accountInserted);
        assertNotEquals(0, paymentInserted.getId());
        System.out.println(paymentInserted.getId());
    }
}
