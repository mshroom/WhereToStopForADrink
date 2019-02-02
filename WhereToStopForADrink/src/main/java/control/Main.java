package control;

import algorithms.Dijkstra;
import algorithms.ShortestPath;
import algorithms.Tsp;
import algorithms.TspNn;
import data.AddressFinder;
import data.Connection;
import data.DistanceFinder;
import dataStructures.PlaceQueue;
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
        GraphController g = new GraphController();
//        g.importPlaces("places.txt");
//        
//        int[][] graph = g.getGraph();
//        
//        for (int i = 0; i < graph.length; i ++) {
//            System.out.println(Arrays.toString(graph[i]));
//        }
//        
//        g.savePlaces();

        g.useSavedPlaces();
        int[][] graph = g.getGraph();
        
        for (int i = 0; i < graph.length; i ++) {
            System.out.println(Arrays.toString(graph[i]));
        }
        int[][] reduced = g.getReducedGraph(700);
        Dijkstra d = new Dijkstra(reduced);
        d.calculateShortestPath();
        System.out.println(d.getDistanceTo(8));
        System.out.println(d.getShortestPath(8));

    }
}
