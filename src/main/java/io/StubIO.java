package io;

import dataStructures.Queue;

/**
 *
 * @author mshroom
 */
public class StubIO implements IO {
    
    private Queue<String> prints;
    private Queue<String> inputs;
    
    public StubIO(Queue<String> inputs) {
        prints = new Queue(new String[10]);
        this.inputs = inputs;
    }

    @Override
    public void printLine(String print) {
        prints.add(print);
    }

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

    @Override
    public String readLine(String instruction) {
        prints.add(instruction);
        String line = inputs.poll();
        printLine("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        printLine("");
        return line;
    }
    
    public Queue<String> getPrints() {
        return this.prints;
    }
}
