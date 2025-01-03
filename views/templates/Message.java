package views.templates;

import java.util.TreeMap;

public class Message {
    public static TreeMap<Integer, String> getBankCardOptions() {
        TreeMap<Integer, String> result = new TreeMap<>();
        result.put(1, "Display balance");
        result.put(2, "Deposit cash");
        result.put(3, "Withdraw cash");
        result.put(4, "Exit");

        return result;
    }

    public static TreeMap<Integer, String> getErrorOptions() {
        TreeMap<Integer, String> result = new TreeMap<>();
        result.put(1, "Enter card number");
        result.put(2, "Exit");

        return result;
    }

    public static void print(String message) {
        System.out.println(message + ":`");
    }

    public static void printWithOptions(String message, TreeMap<Integer, String> options) {
        print(message);
        printOptions(options);
    }

    private static void printOptions(TreeMap<Integer, String> options) {
        for (Integer optionNumber : options.keySet()) {
            String option = String.format(
                    "%d. %s", optionNumber, options.get(optionNumber)
            );

            System.out.println(option);
        }
    }
}
