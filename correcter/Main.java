package correcter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runProgram();
    }

    public static void runProgram() {
        tripleMessage(getInput());
    }

    private static void tripleMessage(String message) {
        StringBuilder tripling = new StringBuilder();
        for (int i = 0; i< message.length(); i++) {
            tripling.append(String.valueOf(message.charAt(i)).repeat(3));
        }
        emulateError(tripling.toString().toCharArray());
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }

    private static String emulateError(char[] phrase) {

        for (int i = 0; i < phrase.length - 2; i += 3) {
            phrase[i + addExtra()] =  generateRandomChar();
        }
        return String.valueOf(phrase);
    }

    private static int addExtra() {
        Random random = new Random();
        return random.nextInt(3);
    }

    private static char generateRandomChar() {
        char symbol;
        int byteCode;
        Random random = new Random();
        do {
            byteCode = random.nextInt(123);
            symbol = (char) byteCode;
        } while (!String.valueOf(symbol).matches("[A-Za-z\\d]"));
        return symbol;
    }
}
