package carsharing.user;

import java.util.Scanner;

public class UserIO {
    private static final Scanner scanner = new Scanner(System.in);

    public static String getInput (String text) {
        System.out.print(text);
        return scanner.nextLine();
    }

    public static void writeOutput (String text) {
        System.out.println(text);
    }
}
