package controllers;

import constants.TableNames;
import enums.ResultStatus;
import db.BankCard;
import db.BankCardDbResult;

import java.sql.*;

public class BankCardController {
    public static BankCardDbResult findBankCard(String number) throws SQLException {
        int numberHash = number.hashCode();
        Connection conn = DriverManager.getConnection("jdbc:h2:/home/bva/atm;IFEXISTS=TRUE", "sa", "");
        Statement connStatement = conn.createStatement();
        ResultSet connResult = connStatement.executeQuery(String.format("SELECT * FROM %s WHERE number_hash=%d", TableNames.BANK_CARD, numberHash));

        if (connResult.next()) {
            return new BankCardDbResult(ResultStatus.SUCCESS, new BankCard(
                connResult.getInt("number_last_digits"),
                connResult.getInt("pin_code"),
                connResult.getInt("holder_id"),
                connResult.getFloat("balance"),
                connResult.getString("number_hash")
            ));
        }

        return new BankCardDbResult(ResultStatus.ERROR, new BankCard());
    }

    public static BankCardDbResult depositCash(BankCard bankCard, double amount) throws SQLException {
        double newBalance = bankCard.getBalance() + amount;

        return updateBalance(bankCard, newBalance);
    }

    public static BankCardDbResult withdrawCash(BankCard bankCard, double amount) throws SQLException {
        double newBalance = bankCard.getBalance() - amount;

        if (newBalance < 0) {
            return new BankCardDbResult(ResultStatus.ERROR, bankCard);
        }

        return updateBalance(bankCard, newBalance);
    }

    private static BankCardDbResult updateBalance(BankCard bankCard, double newBalance) throws SQLException {
        String sql = "UPDATE " + TableNames.BANK_CARD + " SET balance = ? WHERE number_hash = ?";
        Connection conn = DriverManager.getConnection("jdbc:h2:/home/bva/atm;IFEXISTS=TRUE", "sa", "");
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setDouble(1, newBalance);
        updateStatement.setString(2, bankCard.getNumberHash());
        updateStatement.executeUpdate();
        conn.close();

        bankCard.setBalance(newBalance);

        return new BankCardDbResult(ResultStatus.SUCCESS, bankCard);
    }
}
