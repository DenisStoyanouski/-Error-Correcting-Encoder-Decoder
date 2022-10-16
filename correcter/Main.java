package correcter;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runProgram();
    }

    public static void runProgram() {
        simulateSending(readFile());
        /*tripleMessage(getInput());*/
    }

    private static void simulateSending(byte[] message) {
        ArrayList<String> hexSend = new ArrayList<>();
        ArrayList<String> byteSend = new ArrayList<>();
        ArrayList<String> byteReceive = new ArrayList<>();
        final byte[] receiveMessage;
        ArrayList<String> hexReceive = new ArrayList<>();

        for (int i = 0; i < message.length; i++) {
            byteSend.add(Integer.toBinaryString(message[i]));
        }
        System.out.println("Send message in binary string");
        byteSend.forEach(x -> System.out.print(x + " "));
        System.out.println();

        //message to hex array
        for (int i = 0; i < message.length; i++) {
            hexSend.add(Integer.toHexString(message[i]));
        }
        System.out.println("Send message in hex string");
        hexSend.forEach(x -> System.out.print(x + " "));
        System.out.println();

        // message changed a bit in each byte received to binary array
        for (int i = 0; i < message.length; i++) {
            StringBuilder number = new StringBuilder(Integer.toBinaryString(message[i]));
            while (number.length() < 8) {
                number.insert(0, '0');
            }
            int numberOfBit = randomBit();
            if (number.charAt(numberOfBit) == '0') {
                number.setCharAt(numberOfBit, '1');
            } else {
                number.setCharAt(numberOfBit, '0');
            }
            byteReceive.add(number.toString());
        }

        System.out.println("Received message in binary string");
        byteReceive.forEach(x -> System.out.print(x + " "));
        System.out.println();

        //message received to byte array
        System.out.println("Received message in bytes ");
        receiveMessage = new byte[byteReceive.size()];
        for (int j = 0; j < receiveMessage.length; j++) {
            receiveMessage[j] = Byte.parseByte(byteReceive.get(j), 2);
            System.out.print(receiveMessage[j] + " ");
        }
        System.out.println();

        //message received to hex array;
        for (int b = 0; b < receiveMessage.length; b++) {
            hexReceive.add(Integer.toHexString(receiveMessage[b]));
        }
        System.out.println("Received message in hex string");
        hexReceive.forEach(x -> System.out.print(x + " "));
        System.out.println();

        //message in chars
        for (int j = 0; j < receiveMessage.length; j++) {
            System.out.print((char) receiveMessage[j]);
        }
        writeFile(receiveMessage);
    }

    /*private static void tripleMessage(String message) {
        StringBuilder tripling = new StringBuilder();
        for (int i = 0; i< message.length(); i++) {
            tripling.append(String.valueOf(message.charAt(i)).repeat(3));
        }
        System.out.println(tripling);
        emulateError(tripling.toString().toCharArray());
    }*/

    /*private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        System.out.println(input);
        return input;
    }*/

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


    static void writeFile(byte[] array) {
        File receive = new File("./received.txt");
        try (OutputStream outputStream = new FileOutputStream(receive)) {
            outputStream.write(array);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /*private static void emulateError(char[] phrase) {

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
    }*/

    private static int randomBit() {
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
