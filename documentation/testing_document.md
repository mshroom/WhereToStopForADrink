# Testing document

## Travis status

[![Build Status](https://travis-ci.org/mshroom/WhereToStopForADrink.svg?branch=master)](https://travis-ci.org/mshroom/WhereToStopForADrink)

## Codecov test coverage

[![codecov](https://codecov.io/gh/mshroom/WhereToStopForADrink/branch/master/graph/badge.svg)](https://codecov.io/gh/mshroom/WhereToStopForADrink)

## Unit tests

Unit test coverage for the project is good. The UI class has the worst coverage but even there the tests include the most crucial parts of the code. The focus there is mainly to check that the UI calls the methods of other classes in correct situations and with correct parameters. The textual output of the console ui is of less significance.

Some classes have been excluded from the test coverage reports. The classes in the web package, for example, are not very suitable for Unit testing because they require web connection and the responses to the http requests might vary from time to time, depending on the server. The io classes and the GraphStore class that only creates graphs for testing purposes, are also excluded.

## Manual tests

### Web package

The classes in the web package have been tested manually by sending http requests to the server (Digitransit) and checking that the returned data is valid. Experiments were made first with 10 addresses. The results could be validated by comparing them with requests made by browser and Digitransit's GraphiQL user interface.

The address search of the Geocoding API seems to function reliably. However, if the addresses are not formatted correctly, the server will sometimes give correct and sometimes completely wrong coordinates. Using URLEncoder helps.

The itinerary planning of the Routing API works also well for finding distances between coordinates. It turns out, that in some cases the server does not find the route or the distance. This happens sometimes if the two places are too close together - at least I suspect that to be the reason. The problem is quite easy to manage. When the distance between two places is not found, my application will create a graph where there is no edge between them. 

### User interface

User interface has been tested not just with automatic tests, but also by manual testing, trying to find bugs by inserting invalid input or trying to do operations that are not allowed. The ui was also tested by an outsider, in order to find out any faults or bad designs in the ui that the programmer would have missed.

## Performance

The performance of the algorithms can be measured by running the application. Both shortest route algorithms and shortest path algorithms can be compared with various sizes of graphs. Below are the results of tests that have been run 10 times each to get the average speed.

### Route algorithms

| Graph size (V) | Tsp Exact | Tsp Nearest Neighbour |
|---|---|---|
| 5 | 0.29 ms | 0.01 ms |
| 10 | 33.71 ms | 0.02 ms |
| 11 | 222.03 ms | 0.02 ms |
| 12 | 3866.77 ms | 0.02 ms |
| 13 | 5386.09 ms | 0.03 ms |
| 14 | 8730.24 ms | 0.03 ms |
| 15 | 15321.25 ms | 0.03 ms |
| 20* | 5069.35 ms | 0.05 ms |
| 500 | not tested | 1.77 ms |
| 1000 | not tested | 14.63 ms |
| 1500 | not tested | 19.83 ms |
| 2000 | not tested | 57.10 ms |

( * A simple graph where the branch-and-bound method is effective )

![Tsp Exact diagram](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/diagrams/TspExact.png)
![Tsp Nearest Neighbour diagram](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/diagrams/TspNearestNeighbour.png)

The diagrams show only the results of the tests made with a complex graph. Tsp Exact was faster with a simple graph of 20 nodes, as can be seen in the table. All the graphs used with route algorithms were complete.

### Path algorithms

| Graph size (V/E) | Dijkstra | AStar | Bfs |
|---|---|---|---|
| 5/17 | 244554 ns | 150111 ns | 53405 ns |
| 84/496 | 1792496 ns | 211254 ns | 403095 ns |
| 2000/802500* | 130900416 ns | 123038295 ns | 50364073 ns |

( * Used a randomized graph where the number of edges varied with some hundreds each time, this being the average. )

## Algorithm accuracy

The algorithms should in principle always find the shortest path or route (except TspNn, which is an approximation algorithm). However, by making experiments I have noticed that this is not always the case. Because I use the Digitransit platform to get the distances between addresses, the accuracy of each distance is approximately 1 meter. Sometimes if there is a direct path from place A to place B, and place C lies somewhere in the middle, the path A -> C -> B could be 1 meter shorter than the path A -> B. This is, of course, not realistic, but this is how the API behaves.

Because of this inaccuracy, the Bfs algorithm sometimes finds a path that goes directly from place A to place B, while Dijkstra and AStar find a path that goes from place A via place C to place B. The path found by Bfs is shorter in reality, but the API and therefore my application believes it to be 1 meter longer than the path found by Dijkstra and AStar.

For the same reason AStar sometimes finds a path that is 1 meter longer than the path found by Dijkstra. AStar uses Digitransit's distances as distance estimates between places. In the case where there is a path A -> C -> B that is shorter than the direct path A -> B, AStar still uses the A -> B as an estimate. Because the estimate could be longer than the actual shortest path, the algorithm does not in these cases always find the shortest path.
