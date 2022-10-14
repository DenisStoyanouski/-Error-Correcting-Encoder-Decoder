package correcter;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        runProgram();
    }

    public static void runProgram() {
        getInput();
    }

    private static String getInput() {
        String input;
        Scanner scanner = new Scanner(System.in);
        input = scanner.nextLine();
        return input;
    }
}
