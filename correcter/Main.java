package correcter;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runProgram();
    }

    public static void runProgram() {
        char[] phrase = getInput().toCharArray();
        System.out.println(emulateError(phrase));
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }

    private static String emulateError(char[] phrase) {

        for (int i = 0; i < phrase.length; i += 2) {
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
        byteCode = random.nextInt(122);
        symbol = (char) byteCode;
        if (!String.valueOf(symbol).matches("[\\s\\w]")) {
            generateRandomChar();
        }
        return symbol;
    }
}
