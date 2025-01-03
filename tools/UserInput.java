package tools;

import views.templates.Message;

import java.util.Scanner;
import java.util.TreeMap;

public class UserInput {
    public static String getStringValue(String inputMessage) {
        Message.print(inputMessage);
        Scanner scanner = new Scanner(System.in);
        
        return getScannerStringValue(scanner);
    }

    public static int getIntValue(String inputMessage) {
        Message.print(inputMessage);
        Scanner scanner = new Scanner(System.in);

        return getScannerIntValue(scanner);
    }

    public static double getDoubleValue(String inputMessage) {
        Message.print(inputMessage);
        Scanner scanner = new Scanner(System.in);
        
        return getScannerFloatValue(scanner);
    }

    public static int getOption(String inputMessage, TreeMap<Integer, String> options) {
        Message.printWithOptions(inputMessage, options);

        return getInputResult(options.size());
    }
    
    private static int getInputResult(int optionsQuantity) {
        Scanner scanner = new Scanner(System.in);
        int scannerResult = getScannerIntValue(scanner);

        if (scannerResult > optionsQuantity || scannerResult <= 0) {
            throw new NumberFormatException(
                    "Error: incorrect action number"
            );
        }

        return scannerResult;
    }

    private static String getScannerStringValue(Scanner scanner) {
        if (!scanner.hasNext()) {
            throw new NumberFormatException(
                    "Error: incorrect formatted or empty input"
            );
        }

        return scanner.next();
    }

    private static int getScannerIntValue(Scanner scanner) {
        if (!scanner.hasNextInt()) {
            throw new NumberFormatException(
                    "Error: incorrect formatted or empty input"
            );
        }

        return scanner.nextInt();
    }

    private static double getScannerFloatValue(Scanner scanner) {
        if (!scanner.hasNextDouble()) {
            throw new NumberFormatException(
                    "Error: incorrect formatted or empty input"
            );
        }

        return scanner.nextDouble();
    }
}
