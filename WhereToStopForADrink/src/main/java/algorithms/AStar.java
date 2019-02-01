package algorithms;

import dataStructures.MinHeap;
import dataStructures.NodeQueue;
import dataStructures.Queue;

/**
 * AStar algorithm calculates the shortest path to a specific node in a graph.
 *
 * @author mshroom
 */
public class AStar extends ShortestPath {

    private int[][] graph;
    private int[] distanceSaved;
    private int[] distanceEstimate;
    private int[] pathSaved;
    private NodeQueue[] neighbours;
    private int goal;

    /**
     * AStar algorithm calculates the shortest path to a specific node in a
     * graph.
     *
     * @param graph the Graph in the form of a two-dimensional array.
     * @param distanceEstimate An array containing distance estimates between
     * the goal and every other node. The algorithm only finds the shortest path
     * if no distances are over-estimated.
     * @param goal The index of the goal node.
     */
    public AStar(int[][] graph, int[] distanceEstimate, int goal) {
        super(graph);
        this.graph = graph;
        this.goal = goal;
        this.distanceEstimate = distanceEstimate;
        this.createSavedArrays();
        this.convertGraph(graph);
    }

    /**
     * Method creates arrays to store data from previous searches in this graph.
     * This makes it faster to find a path to a different goal.
     */
    private void createSavedArrays() {
        this.distanceSaved = new int[graph.length];
        this.pathSaved = new int[graph.length];
        for (int i = 0; i < distanceSaved.length; i++) {
            distanceSaved[i] = -1;
            pathSaved[i] = -1;
        }
    }

    /**
     * Method converts the graph to an adjacency list.
     * @param graph the original graph in the form of a two-dimensional array
     */
    @Override
    protected void convertGraph(int[][] graph) {
        NodeQueue[] n = new NodeQueue[graph.length];
        for (int i = 0; i < graph.length; i++) {
            NodeQueue nodes = new NodeQueue(10);
            for (int j = 0; j < graph.length; j++) {
                if (i != j && graph[i][j] >= 0) {
                    nodes.add(new NodeWithEstimate(j, graph[i][j], distanceEstimate[j]));
                }
            }
            n[i] = nodes;
        }
        this.neighbours = n;
    }

    /**
     * Method calculates the shortest path from starting node to goal.
     */
    @Override
    public void calculateShortestPath() {
        this.initialize();
        NodeQueue[] neighboursCopy = this.neighbours.clone();
        MinHeap heap = new MinHeap(neighbours.length * neighbours.length);
        heap.add(new NodeWithEstimate(0, 0, 0));
        while (!heap.isEmpty()) {
            Node smallest = heap.poll();
            if (smallest.getIndex() == goal) {
                break;
            }
            if (visited[smallest.getIndex()] == 0) {
                visited[smallest.getIndex()] = 1;
                while (!neighboursCopy[smallest.getIndex()].isEmpty()) {
                    Node node = neighboursCopy[smallest.getIndex()].poll(); 
                    if (distance[node.getIndex()] == -1 || distance[node.getIndex()] > distance[smallest.getIndex()] + node.getDistance()) {
                        distance[node.getIndex()] = distance[smallest.getIndex()] + node.getDistance();
                        path[node.getIndex()] = smallest.getIndex();
                        heap.add(new NodeWithEstimate(node.getIndex(), distance[node.getIndex()], distanceEstimate[node.getIndex()]));
                    }
                }
            }
        }
        this.saveResult();
    }

    /**
     * Method saves the results of the search.
     */
    private void saveResult() {
        for (int i = 0; i < distance.length; i++) {
            if (this.distance[i] != -1) {
                this.distanceSaved[i] = distance[i];
                this.pathSaved[i] = path[i];
            }
        }
        if (distance[this.goal] == -1) {
            distanceSaved[goal] = -2;
            pathSaved[goal] = -2;
        }
    }

    /**
     * Method returns the shortest path from starting node to the given node.
     *
     * @param node the index of the goal node
     * @return a String describing the path or informing if it has not been
     * found.
     */
    @Override
    public String getShortestPath(int node) throws Throwable {
        Queue stack = new Queue(10);
        int previous = pathSaved[node];
        if (previous == -1) {
            return "Algorithm has not yet searched a path to this node."
                    + "Please make another search first.";
        } else if (previous == -2) {
            return "There is no path";
        }
        while (previous != 0) {
            stack.push(previous);
            previous = path[previous];
        }
        String ret = "0 > ";
        while (!stack.isEmpty()) {
            ret = ret + (stack.poll() + " > ");
        }
        return "" + ret + node;
    }

    /**
     * Method returns the shortest distance to the given node.
     *
     * @param node the index of the goal node
     * @return the length of the path, -2 if there is no path to the node and -1
     * if the node was not yet searched
     */
    @Override
    public int getDistanceTo(int node) {
        return distanceSaved[node];
    }

    /**
     * Method makes another search for the shortest path to the given node.
     *
     * @param node The index of the goal node.
     * @param distanceEstimate An array containing distance estimates between
     * the goal and every other node.
     */
    public void search(int node, int[] distanceEstimate) {
        this.goal = goal;
        this.distanceEstimate = distanceEstimate;
        this.convertGraph(graph);
        this.calculateShortestPath();
    }
}
