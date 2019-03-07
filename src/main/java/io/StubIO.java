package io;

import dataStructures.Queue;

/**
 * StubIO is used in tests to store inputs and outputs.
 * @author mshroom
 */
public class StubIO implements IO {
    
    private Queue<String> prints;
    private Queue<String> inputs;
    
    /**
     * Creates a StubIO with the given list of input lines.
     * @param inputs A Queue containing input text lines.
     */
    public StubIO(Queue<String> inputs) {
        prints = new Queue(new String[10]);
        this.inputs = inputs;
    }

    /**
     * Adds the given text line to the list of output lines.
     * @param print The text to be added.
     */
    @Override
    public void printLine(String print) {
        prints.add(print);
    }

    /**
     * Removes and returns the next integer in the list of input lines.
     * @param instruction The instruction to be added to the list of output lines.
     * @return The next integer in the list of input lines.
     */
    @Override
    public int readInt(String instruction) {
        prints.add(instruction);
        int number = -1;
        while (true) {
            try {
                number = Integer.parseInt(inputs.poll());
                break;
            } catch (Exception e) {
                prints.add("Please enter a number");
            }
        }
        printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printLine("");
        return number;
    }

    /**
     * Removes and returns the next text line in the list of input lines.
     * @param instruction The instruction to be added to the list of output lines.
     * @return The next text line in the list of input lines.
     */
    @Override
    public String readLine(String instruction) {
        prints.add(instruction);
        String line = inputs.poll();
        printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printLine("");
        return line;
    }
    
    /**
     * Returns the list of output lines.
     * @return A Queue containing the output lines.
     */
    public Queue<String> getPrints() {
        return this.prints;
    }
}
