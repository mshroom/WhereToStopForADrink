package io;

import java.util.Scanner;

/**
 * ConsoleIO is used to communicate with the user in the console.
 * @author mshroom
 */
public class ConsoleIO implements IO {

    private Scanner scanner = new Scanner(System.in);

    /**
     * Prints the given line of text.
     * @param print The line to be printed.
     */
    @Override
    public void printLine(String print) {
        System.out.println(print);
    }

    /**
     * Prints the given instruction and reads and returns the next integer entered by the user.
     * @param instruction The instruction to be printed.
     * @return The number entered by the user.
     */
    @Override
    public int readInt(String instruction) {
        System.out.println(instruction);
        int number = -1;
        while (true) {
            try {
                number = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a number");
            }
        }
        printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printLine("");
        return number;
    }

    /**
     * Prints the given instruction and reads and returns the next text line entered by the user.
     * @param instruction The instruction to be printed.
     * @return The text line entered by the user.
     */
    @Override
    public String readLine(String instruction) {
        System.out.println(instruction);
        String line = scanner.nextLine();
        printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printLine("");
        return line;
    }
}
