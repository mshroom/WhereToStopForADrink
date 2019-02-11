package io;

import java.util.Scanner;

/**
 *
 * @author mshroom
 */
public class ConsoleIO implements IO {

    private Scanner scanner = new Scanner(System.in);

    @Override
    public void printLine(String print) {
        System.out.println(print);
    }

    @Override
    public int readInt(String instruction) {
        System.out.println(instruction);
        int i = -1;
        while (true) {
            try {
                i = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Please enter a number");
            }
        }
        return i;
    }

    @Override
    public String readLine(String instruction) {
        System.out.println(instruction);
        String line = scanner.nextLine();
        printLine("");
        return line;
    }
}
