package io;

/**
 * Interface for IO objects.
 * @author mshroom
 */
public interface IO {
    void printLine(String print);
    String readLine(String instruction);
    int readInt(String instruction);
}
