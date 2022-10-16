package correcter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

    private static byte[] readFile() {
        byte[] message = null;
        File file = new File("./send.txt");
        try (FileInputStream reader = new FileInputStream(file)) {
            message = reader.readAllBytes();
        } catch (IOException e) {
            System.out.println("File not exist");
        }
        return message;
    }

    private static void emulateError(char[] phrase) {

        for (int i = 0; i < phrase.length - 2; i += 3) {
            phrase[i + addExtra()] =  generateRandomChar(phrase[i]);
        }
        System.out.println(String.valueOf(phrase));
        decodeMessage(phrase);
    }

    private static void decodeMessage(char[] phrase) {
        StringBuilder decode = new StringBuilder();
        for (int i = 0; i < phrase.length - 2; i += 3) {
            if (phrase[i] != phrase[i + 1] && phrase[i] != phrase [i + 2]) {
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

    private static char generateRandomChar(char symbol) {
        char symbolRandom;
        int byteCode;
        Random random = new Random();
        do {
            byteCode = random.nextInt(123);
            symbolRandom = (char) byteCode;
        } while (!String.valueOf(symbolRandom).matches("[A-Za-z\\d]"));

        return symbolRandom != symbol ? symbolRandom : ++symbolRandom;
    }
}
