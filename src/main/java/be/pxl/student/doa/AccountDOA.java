package be.pxl.student.doa;

import be.pxl.student.entity.Account;
import be.pxl.student.entity.Payment;

import java.sql.*;
import java.util.List;


public class AccountDOA {
    private static final String SELECT_BY_ID = "SELECT * FROM contacts WHERE id = ?";
    private static final String SELECT_BY_NAME = "SELECT * FROM contacts WHERE name = ?";
    private static final String UPDATE = "UPDATE contacts SET name=?, phone=?, email=? WHERE id = ?";
    private static final String INSERT = "INSERT INTO contacts (name, phone, email) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM contacts WHERE id = ?";
    private String url;
    private String user;
    private String password;

    public AccountDOA() {

    }

    public Account createAccount(Account account) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, account.getName());
            stmt.setString(2, account.getIBAN());
            //stmt.setString(3, account.);
            if (stmt.executeUpdate() == 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        account.setIBAN(rs.getString(1));
                        return account;
                    }
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;

    }

    public boolean deleteAccount(Account account) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(DELETE)) {
            stmt.setString(4, account.getIBAN());
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public boolean updateAccount(Account accout) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(UPDATE)) {
            stmt.setString(1, accout.getIBAN());
            stmt.setString(2, accout.getName());
            stmt.setObject(3, accout.getPayments());
            return stmt.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public Account readAccount(String name) {
        try (Connection connection = getConnection(); PreparedStatement stmt = connection.prepareStatement(SELECT_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapAccount(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    private Account mapAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setIBAN(rs.getString("IBAN"));
        account.setName(rs.getString("Name"));
        account.setPayments((List<Payment>) rs.getObject("Payments"));
        return account;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);

    }
}
