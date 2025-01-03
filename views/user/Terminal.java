package views.user;

import java.sql.SQLException;
import java.util.TreeMap;

import controllers.BankCardController;
import db.BankCard;
import enums.ResultStatus;
import db.BankCardDbResult;
import tools.UserInput;
import views.templates.Message;

public class Terminal {
    public static void main(String[] args) throws SQLException {
        try {
            String bankCardNumber = UserInput.getStringValue("Welcome to ATM. Please enter card number");
            BankCardDbResult bankCardDbResult = BankCardController.findBankCard(bankCardNumber);

            if (bankCardDbResult.resultStatus() == ResultStatus.ERROR) {
                errorInputResult(args);

                return;
            }

            if (bankCardDbResult.resultStatus() == ResultStatus.SUCCESS) {
                BankCard bankCard = bankCardDbResult.bankCard();

                int pinCodeInput = UserInput.getIntValue("Please enter pin-code");
                int pinCodeDb = bankCard.getPinCode();

                if (pinCodeDb != pinCodeInput) {
                    throw new NumberFormatException("Error: invalid pin code");
                }

                chooseBankCardOption(bankCardDbResult, bankCard);
            }
        } catch (SQLException | IllegalArgumentException e) {
            String errorMessage = e instanceof SQLException
                    ? "Error code: " + ((SQLException) e).getErrorCode()
                    : e.getMessage();

            System.out.println(errorMessage);
            errorInputResult(args);
        }
    }

    private static void chooseBankCardOption(BankCardDbResult bankCardDbResult, BankCard bankCard) throws SQLException {
        TreeMap<Integer, String> bankCardOptions = Message.getBankCardOptions();

        int bankCardOption = UserInput.getOption(
                "Please enter option number:",
                bankCardOptions
        );

        switch (bankCardOption) {
            case 1:
                System.out.println("Card balance: " + bankCardDbResult.bankCard().getBalance());
                chooseBankCardOption(bankCardDbResult, bankCard);

            case 2:
                double depositAmount = UserInput.getDoubleValue("Please enter cash amount to deposit");
                BankCardDbResult bankCardDepositUpdated = BankCardController.depositCash(bankCard, depositAmount);
                System.out.println("Successful cash deposit");
                chooseBankCardOption(bankCardDbResult, bankCardDepositUpdated.bankCard());

            case 3:
                double withdrawAmount = UserInput.getDoubleValue("Please enter cash amount to withdraw");
                BankCardDbResult bankCardWithdrawUpdated = BankCardController.withdrawCash(bankCard, withdrawAmount);
                ResultStatus resultStatus = bankCardDbResult.resultStatus();

                if (resultStatus == ResultStatus.SUCCESS) {
                    System.out.println("Successful cash withdraw");
                }

                if (resultStatus == ResultStatus.ERROR) {
                    System.out.println("Error cash withdraw: not enough balance");
                }

                chooseBankCardOption(bankCardDbResult, bankCardWithdrawUpdated.bankCard());

            default:
                Message.print("Bye!");
        }
    }

    private static void errorInputResult(String[] args) throws SQLException {
        TreeMap<Integer, String> errorOptions = Message.getErrorOptions();

        int userAction = UserInput.getOption(
                "Some error. Please enter option number",
                errorOptions
        );

        if (userAction == 1) {
            main(args);
        } else {
            Message.print("Bye!");
        }
    }
}
