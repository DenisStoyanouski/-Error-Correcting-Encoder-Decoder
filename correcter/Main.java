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
        System.out.println(tripling);
        emulateError(tripling.toString().toCharArray());
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        System.out.println(input);
        return input;
    }

    private static void emulateError(char[] phrase) {

        for (int i = 0; i < phrase.length - 2; i += 3) {
            phrase[i + addExtra()] =  generateRandomChar();
        }
        System.out.println(String.valueOf(phrase));
        decodeMessage(phrase);
    }

    private static void decodeMessage(char[] phrase) {
        StringBuilder decode = new StringBuilder();
        for (int i = 0; i < phrase.length - 2; i += 3) {
            if (phrase[i] != phrase[i + 1] && phrase[i] == phrase [i + 2]) {
                decode.append(phrase[i + 1]);
            } else {
                decode.append(phrase[i]);
            }
        }
        System.out.println(decode);
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
