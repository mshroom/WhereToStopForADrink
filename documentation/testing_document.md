# Testing document

## Travis status

[![Build Status](https://travis-ci.org/mshroom/WhereToStopForADrink.svg?branch=master)](https://travis-ci.org/mshroom/WhereToStopForADrink)

## Codecov test coverage

[![codecov](https://codecov.io/gh/mshroom/WhereToStopForADrink/branch/master/graph/badge.svg)](https://codecov.io/gh/mshroom/WhereToStopForADrink)

## Automatic tests

JUnit tests are used to test the application automatically. Test coverage for the project is good. The UI class has the worst coverage but even there the tests include the most crucial parts of the code. The focus there is mainly to check that the UI calls the methods of other classes in correct situations and with correct parameters. The textual output of the console ui is of less significance.

Some classes have been excluded from the test coverage reports. The classes in the web package, for example, are not very suitable for JUnit testing because they require web connection and the responses to the http requests might vary from time to time, depending on the server. The io classes and the GraphStore class that only creates graphs for testing purposes, are also excluded.

## Manual tests

### Web package

The classes in the web package have been tested manually by sending http requests to the server (Digitransit) and checking that the returned data is valid. Experiments were made first with 10 addresses. The results could be validated by comparing them with requests made by browser and Digitransit's GraphiQL user interface.

The address search of the Geocoding API seems to function reliably. However, if the addresses are not formatted correctly, the server will sometimes give correct and sometimes completely wrong coordinates. Using URLEncoder helps.

The itinerary planning of the Routing API works also well for finding distances between coordinates. It turns out, that in some cases the server does not find the route or the distance. This happens sometimes if the two places are too close together - at least I suspect that to be the reason. The problem is quite easy to manage. When the distance between two places is not found, my application will create a graph where there is no edge between them. 

### User interface

User interface has been tested not just with automatic tests, but also by manual testing, trying to find bugs by inserting invalid input or trying to do operations that are not allowed. The ui was also tested by an outsider, in order to find out any faults or bad designs in the ui that the programmer would have missed.

## Performance

The performance of the algorithms can be measured by running the application. Both shortest route algorithms and shortest path algorithms can be compared with various sizes of graphs. Below are the results of tests that have been run 10 times each to get the average speed. Graphs were created either from real data (using the addresses of 84 bars as an example) or, in case of bigger graphs, by generating graphs with random distances. Complete graphs were used with route algorithms and incomplete graphs with path algorithms.

### Route algorithms

As can be seen in the table and the diagrams, Tsp Exact is very slow except with very small graphs. Tsp Nearest Neighbour, on the other hand, is very fast even with big input. Yet the accuracy of Tsp Nearest Neighbour tends to get worse when the graph size grows.

#### Tests made with simple test graphs

| Graph size (V) | Tsp Exact | Tsp Nearest Neighbour | Tsp NN found the shortest route (% of all cases)  | Average difference in route length |
|---|---|---|---|---|
| 5 | 0.11 ms | 0.00 ms | 100 % | 0 % |
| 20 | 5069.35 ms | 0.05 ms | 100 % | 0 % |

The test graph with 20 nodes is simple graph where the branch-and-bound method is effective. That is why even Tsp Exact is 
quite fast.

### Tests made with random test graphs

| Graph size (V) | Tsp Exact | Tsp Nearest Neighbour | Tsp NN found the shortest route (% of all cases)  | Average difference in route length |
|---|---|---|---|---|
| 5 | 0.23 ms | 0.01 ms | 40 % | 13,4 % |
| 10 | 38.17 ms | 0.02 ms | 10 % | 22,8 % |
| 11 | 32.08 ms | 0.02 ms | 0 % | 26,15 % |
| 12 | 52.78 ms | 0.03 ms | 10 % | 22.3 % |
| 13 | 239.30 ms | 0.03 ms |  |  |
| 14 | 902.02 ms | 0.03 ms | 0 % |  |
| 15 | 2991.65 ms | 0.03 ms | 0 % |  |
| 500 | not tested | 1.77 ms | - | - |
| 1000 | not tested | 14.63 ms | - | - |
| 1500 | not tested | 19.83 ms | - | - |
| 2000 | not tested | 23.53 ms | - | - |

### Tests made with graphs using real imported data

| Graph size (V) | Tsp Exact | Tsp Nearest Neighbour | Tsp NN found the shortest route (% of all cases)  | Average difference in route length |
|---|---|---|---|---|
| 5 | 0.29 ms | 0.01 ms | 50 % | 2,0 % |
| 10 | 33.71 ms | 0.02 ms | 10 % | 10,5 % |
| 11 | 222.03 ms | 0.02 ms | 20 % | 9,3 % |
| 12 | 3866.77 ms | 0.02 ms | 0 % | 7,7 % |
| 13 | 5386.09 ms | 0.03 ms | 0 % | 13,2 % |
| 14 | 8730.24 ms | 0.03 ms | 0 % | 12,9 % |
| 15 | 25321.25 ms** | 0.03 ms | 0 % | 14,4 % |

( ** A couple of times the test was interrupted after waiting more than 5 minutes. These tests are excluded from the average time, as it was unclear whether this was because the algorithm was just slow or because there were some faults in the graph data (see algorithm accuracy section below) )

![Tsp Exact diagram](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/diagrams/TspExact.png)
![Tsp Nearest Neighbour diagram](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/diagrams/TspNearestNeighbour.png)

The diagrams show only the results of the tests made with a complex graph. Tsp Exact was not tested with graphs bigger than 15 nodes, as it was too slow. An exception was a simple graph of 20 nodes, which Tsp Exact could solve quite fast. As this is a special case and not directly compareable with the other results, it is not shown in the diagram. 

### Path algorithms

The path algorithms all perform well even with big input. There are not that radical differences between the algorithms, except that Bfs begins to be faster with big graphs. A* is usually a little faster than Dijkstra, though not always.

| Graph size (V/E) | Dijkstra | A* | Bfs |
|---|---|---|---|
| 5/17 | 0.24 ms | 0.15 ms | 0.05 ms |
| 84/496 | 1.79 ms | 0.21 ms | 0.40 ms |
| 500/51000* | 8.37 ms | 6.16 ms | 9.71 ms |
| 1000/202000* | 19.22 ms | 54.35 ms | 13.45 ms |
| 1500/452000* | 118.82 ms | 104.19 ms | 35.56 ms |
| 2000/802500* | 130.90 ms | 123.04 ms | 50.36 ms |

( * Used a randomized graph where the number of edges varied with some hundreds each time, this being the average. )

![Path algorithms performance diagram](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/diagrams/PathAlgorithms.png)

## Algorithm accuracy

The algorithms should in principle always find the shortest path or route (except Tsp Nearest Neighbour, which is an approximation algorithm). However, by making experiments I have noticed that this is not always the case. Because I use the Digitransit platform to get the distances between addresses, the accuracy of each distance is approximately 1 meter. Sometimes if there is a direct path from place A to place B, and place C lies somewhere in the middle, the path A -> C -> B could be 1 meter shorter than the path A -> B. This is, of course, not realistic, but this is how the API behaves.

Because of this inaccuracy, Bfs sometimes finds a path that goes directly from place A to place B, while Dijkstra and A* find a path that goes from place A via place C to place B. The path found by Bfs is shorter in reality, but the API and therefore my application believes it to be 1 meter longer than the path found by Dijkstra and A*.

For the same reason A* sometimes finds a path that is 1 meter longer than the path found by Dijkstra. A* uses Digitransit's distances as distance estimates between places. In the case where there is a path A -> C -> B that is shorter than the direct path A -> B, A* still uses the A -> B as an estimate. Because the estimate could be longer than the actual shortest path, the algorithm does not in these cases always find the shortest path.

Route algorithms, too, might be affected by the incoherence in graph data, since the graph is not complete if all distances are not found. I thought this could have been the reason why Tsp Exact sometimes gets slower or even stuck when using a graph of 15 nodes as input. I tried to remove all infinite distances from the graph by replacing them with a non-infinite distance value, but this did not seem to have any effect on the performance. 
