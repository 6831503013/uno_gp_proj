package utils;

import java.util.Scanner;

public class InputHandler {

    private static Scanner scanner = new Scanner(System.in);

    public static int getInt(String message) {
        System.out.print(message);
        // TODO: Validate input
        return scanner.nextInt();
    }

    public static String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }
}
