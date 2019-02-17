package algorithms;

import dataStructures.Node;
import dataStructures.MinHeap;
import dataStructures.ObjectQueue;

/**
 * Class calculates the shortest path from starting node to all other nodes in a
 * graph.
 *
 * @author mshroom
 */
public class Dijkstra extends ShortestPath {

    private ObjectQueue[] neighbours;

    public Dijkstra(int[][] graph) {
        super(graph);
        this.convertGraph(graph);
    }

    /**
     * Method converts the graph to an adjacency list.
     * @param graph the original graph in the form of a two-dimensional array
     */
    @Override
    protected void convertGraph(int[][] graph) {
        ObjectQueue[] neighboursList = new ObjectQueue[graph.length];
        for (int i = 0; i < graph.length; i++) {
            ObjectQueue nodes = new ObjectQueue(10);
            for (int j = 0; j < graph.length; j++) {
                if (i != j && graph[i][j] >= 0) {
                    nodes.add(new Node(j, graph[i][j]));
                }
            }
            neighboursList[i] = nodes;
        }
        this.neighbours = neighboursList;
    }

    /**
     * Method calculates the shortest path from node 0 to all other nodes.
     */
    @Override
    public void calculateShortestPath() {
        this.initialize();
        ObjectQueue[] neighboursCopy = this.neighbours.clone();
        MinHeap heap = new MinHeap(neighbours.length * neighbours.length);
        heap.add(new Node(0, 0));
        while (!heap.isEmpty()) {
            Node smallest = heap.poll();
            if (visited[smallest.getIndex()] == 0) {
                visited[smallest.getIndex()] = 1;
                while (!neighboursCopy[smallest.getIndex()].isEmpty()) {
                    Node node = (Node) neighboursCopy[smallest.getIndex()].poll();                
                    if (distance[node.getIndex()] == -1 || distance[node.getIndex()] > distance[smallest.getIndex()] + node.getDistance()) {
                        distance[node.getIndex()] = distance[smallest.getIndex()] + node.getDistance();
                        path[node.getIndex()] = smallest.getIndex();
                        heap.add(new Node(node.getIndex(), distance[node.getIndex()]));                        
                    }
                }
            }
        }
    }
}
