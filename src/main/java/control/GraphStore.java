package control;

import java.util.Random;

/**
 * Class contains a set of graphs for testing purposes.
 *
 * @author mshroom
 */
public class GraphStore {

    /**
     * Creates a small graph for pathfinding algorithms.
     *
     * @return Graph in the form of a two-dimensional array.
     */
    public int[][] createSmallGraphForPathfinding() {
        int[][] small = {
            {0, 1, 1, -1, -1, -1, -1},
            {1, 0, -1, -1, 1, -1, -1},
            {1, -1, 0, 2, 2, -1, 4},
            {-1, -1, 2, 0, -1, -1, 3},
            {-1, 1, 2, -1, 0, 1, -1},
            {-1, -1, -1, -1, 1, 0, 1},
            {-1, -1, 4, 3, -1, 1, 0}
        };
        return small;
    }

    /**
     * Creates a small graph for pathfinding algorithms.
     *
     * @return Graph in the form of a two-dimensional array.
     */
    public int[][] createSmallGraphForPathfinding2() {
        int[][] smallGraph = {
            {0, 60, -1, -1, 95},
            {60, 0, 60, -1, 40},
            {110, 60, 0, 60, 40},
            {101, 110, 60, 0, 95},
            {95, 40, 40, 95, 0}
        };
        return smallGraph;
    }

    /**
     * Creates a big graph for path-finding algorithms.
     *
     * @return Graph in the form of a two-dimensional array.
     */
    public int[][] createBigSimpleGraphForPathfinding() {
        int[][] big = new int[100][100];
        for (int i = 0; i < big.length; i++) {
            for (int j = 0; j < big.length; j++) {
                if (i == 0 || j == 0 || i == 99 || j == 99) {
                    big[i][j] = -1;
                } else if (i - j == 1 || i - j == -1) {
                    big[i][j] = 1;
                } else if (i != j) {
                    big[i][j] = -1;
                }
            }
        }
        big[0][1] = 1;
        big[1][0] = 1;
        big[0][99] = 90;
        big[99][0] = 90;
        big[99][98] = 1;
        big[98][99] = 1;
        return big;
    }

    /**
     * Creates a big graph for pathfinding algorithms, where the shortest path.
     *
     * @return Graph in the form of a two-dimensional array.
     */
    public int[][] createBigRandomGraphForPathfinding() {
        Random random = new Random();
        int[][] graph = new int[2000][2000];
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 2000; j++) {
                if (i != j) {
                    if (graph[j][i] != 0) {
                        graph[i][j] = graph[j][i];
                    } else if (j == i + 1) { // make sure at least one path exists
                        graph[i][j] = random.nextInt(990) + 1;
                    } else {
                        int isPath = random.nextInt(5);
                        if (isPath == 4) {
                            graph[i][j] = random.nextInt(990) + 51;
                        } else {
                            graph[i][j] = -1;
                        }
                    }
                }
            }
        }
        return graph;
    }

    /**
     * Creates a graph for path-finding algorithms, in which there is no path to
     * node 6.
     *
     * @return Graph in the form of a two-dimensional array
     */
    public int[][] createGraphWithNoPath() {
        int[][] graph = {
            {0, 1, 1, 1, 1, 1, -1},
            {1, 0, 1, 1, 1, 1, -1},
            {1, 1, 0, 1, 1, 1, -1},
            {1, 1, 1, 0, 1, 1, -1},
            {1, 1, 1, 1, 0, 1, -1},
            {1, 1, 1, 1, 1, 0, -1},
            {-1, -1, -1, -1, -1, -1, 0}
        };
        return graph;
    }

    /**
     * Creates a distance array for AStar algorithm. Unknown distances will be
     * given value 1 so that the algorithm will not overestimate any distances.
     *
     * @param graph The graph used in pathfinding.
     * @param goal The index of the goal node.
     * @return An array containing distance estimates.
     */
    public int[] createFakeDistancesForAStarGraph(int[][] graph, int goal) {
        int[] distances = graph[goal];
        for (int i : distances) {
            if (i == -1) {
                i = 1;
            }
        }
        return distances;
    }

    /**
     * Creates a small complete graph for travelling salesman algorithms.
     *
     * @return Graph in the form of a two-dimensional array
     */
    public int[][] createSmallCompleteGraph() {
        // shortest route 0 > 2 > 4 > 1 > 3 = 5
        int[][] small = {
            {0, 5, 1, 1, 5},
            {5, 0, 5, 1, 1},
            {1, 5, 0, 5, 1},
            {1, 1, 5, 0, 5},
            {5, 1, 1, 5, 0}
        };
        return small;
    }

    /**
     * Creates a big complete graph for travelling salesman algorithms.
     *
     * @return Graph in the form of a two-dimensional array
     */
    public int[][] createBigCompleteGraph() {
        // shortest route 0 > 2 > 4 > 1 > 3 > 5 > 6 > 7 > 8 > 9 > 10 > 11 > 12 > 13 > 14 > 15 > 16 > 17 > 18 > 19 = 20
        int[][] big = {
            {0, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1},
            {5, 0, 5, 1, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {1, 5, 0, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 1, 5, 0, 5, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 1, 1, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 1, 5, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1, 5},
            {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0, 1},
            {1, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 1, 0}
        };
        return big;
    }

    /**
     * Creates a big random complete graph for travelling salesman algorithms.
     * Can be used only with approximation algorithms, way too big for exact algorithms.
     * @return Graph in the form of a two-dimensional array.
     */
    public int[][] createBigRandomCompleteGraph() {
        Random random = new Random();
        int[][] graph = new int[2000][2000];
        for (int i = 0; i < 2000; i++) {
            for (int j = 0; j < 2000; j++) {
                if (i != j) {
                    if (graph[j][i] != 0) {
                        graph[i][j] = graph[j][i];
                    } else {
                        graph[i][j] = random.nextInt(990) + 51;
                    }
                }
            }
        }
        return graph;
    }

    /**
     * Creates a small complete graph for travelling salesman algorithms, where
     * the nearest neighbour strategy will not produce an optimal solution.
     *
     * @return Graph in the form of a two-dimensional array
     */
    public int[][] createNotOptimalCompleteGraph() {
        // shortest route 0 > 2 > 1 > 3 > 4 = 14
        // nearest neighbour strategy finds 0 > 1 > 2 > 3 > 4 = 15        
        int[][] notOptimal = {
            {0, 4, 5, 5, 5},
            {4, 0, 1, 2, 5},
            {5, 1, 0, 4, 5},
            {5, 2, 4, 0, 1},
            {5, 5, 5, 1, 0}
        };
        return notOptimal;
    }
}
