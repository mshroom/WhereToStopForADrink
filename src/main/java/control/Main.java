package control;

import algorithms.Dijkstra;
import algorithms.ShortestPath;
import algorithms.Tsp;
import algorithms.TspNn;
import web.AddressFinder;
import web.Connection;
import web.DistanceFinder;
import dataStructures.ObjectQueue;
import io.IO;
import io.TextIO;
import java.io.*;
import java.util.Scanner;
import ui.TextUI;
import java.net.*;
import java.util.Arrays;

/**
 *
 * @author mshroom
 */

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception, Throwable {    
        IO io = new TextIO();
        TextUI ui = new TextUI(io);
        ui.start();        
    }
}
