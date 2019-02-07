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

        
//        GraphController g = new GraphController();
//        g.importPlaces("places.txt");
//        
//        int[][] graph = g.getGraph();
//        
//        for (int i = 0; i < graph.length; i ++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        
//        g.savePlaces();

//        g.useSavedPlaces();
//        int[][] graph = g.getGraph();
//        
//        for (int i = 0; i < graph.length; i ++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        int[][] reduced = g.getReducedGraph(700);
//        Dijkstra d = new Dijkstra(reduced);
//        d.calculateShortestPath();
//        System.out.println(d.getDistanceTo(8));
//        System.out.println(d.getShortestPath(8));

//          AddressFinder a = new AddressFinder();
//          DistanceFinder d = new DistanceFinder();
//          String[] co  =a.findCoordinates("Vaasankatu 29 Helsinki");
//          System.out.println(co[0]);
//          System.out.println(co[1]);
//          String[] co2 = a.findCoordinates("Vaasankatu 23 Helsinki");
//          System.out.println(co2[0]);
//          System.out.println(co2[1]);          
//          int i = d.findDistance(new Place(0, "", "", co[0], co[1]), new Place(1, "", "", co2[0], co2[1]));
//          System.out.println(i);
        
    }
}
