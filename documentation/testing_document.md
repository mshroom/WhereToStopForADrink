# Testing document

## Travis status

[![Build Status](https://travis-ci.org/mshroom/WhereToStopForADrink.svg?branch=master)](https://travis-ci.org/mshroom/WhereToStopForADrink)

## Codecov test coverage

[![codecov](https://codecov.io/gh/mshroom/WhereToStopForADrink/branch/master/graph/badge.svg)](https://codecov.io/gh/mshroom/WhereToStopForADrink)

## Unit tests

Unit test coverage for the project is good, except for the ui and web packages that, at the moment, have no automatic tests. One reason for that is that there will probably still be big changes to the text ui design (the texts to be printed etc). The classes in the web package, on the other hand, are not very suitable for Unit testing because they require web connection and the responses to the http requests might vary from time to time, depending on the server. These classes are therefore excluded from the test coverage reports.

## Manual tests

### Web package

The classes in the web package have been tested manually by sending http requests to the server (Digitransit) and checking that the returned data is valid. Experiments were made first with 10 addresses. The results could be validated by comparing them with requests made by browser and Digitransit's GraphiQL user interface.

The address search of the Geocoding API seems to function reliably. However, if the addresses are not formatted correctly, the server will sometimes give correct and sometimes completely wrong coordinates. Using URLEncoder helps.

The itinerary planning of the Routing API works also well for finding distances between coordinates. It turns out, that in some cases the server does not find the route or the distance. This happens sometimes if the two places are too close together - at least I suspect that to be the reason. The problem is quite easy to manage. When the distance between two places is not found, my application will create a graph where there is no edge between them. 

### User interface

TODO

## Performance

The performance of the algorithms can be measured by running the application. Both shortest route algorithms and shortest path algorithms can be compared with various sizes of graphs. Below are the results of tests that have been run 10 times each to get the average speed.

### Route algorithms

| Graph size | Tsp | TspNn |
|---|---|---|
| 5 | 286225 ns | 13425 ns |
| 10 | 33708838 ns | 20221 ns |
| 15 | 15321250008 ns | 29430 ns |

### Path algorithms

| Graph size | Dijkstra | AStar | Bfs |
|---|---|---|---|
| 5 | 244554 ns | 150111 ns | 53405 ns |
| 83 | 1792496 ns | 211254 ns | 403095 ns |

## Algorithm accuracy

The algorithms should in principle always find the shortest path or route (except TspNn, which is an approximation algorithm). However, by making experiments I have noticed that this is not always the case. Because I use the Digitransit platform to get the distances between addresses, the accuracy of each distance is approximately 1 meter. Sometimes if there is a direct path from place A to place B, and place C lies somewhere in the middle, the path A -> C -> B could be 1 meter shorter than the path A -> B. This is, of course, not realistic, but this is how the API behaves.

Because of this inaccuracy, the Bfs algorithm sometimes finds a path that goes directly from place A to place B, while Dijkstra and AStar find a path that goes from place A via place C to place B. The path found by Bfs is shorter in reality, but the API and therefore my application believes it to be 1 meter longer than the path found by Dijkstra and AStar.

For the same reason AStar sometimes finds a path that is 1 meter longer than the path found by Dijkstra. AStar uses Digitransit's distances as distance estimates between places. In the case where there is a path A -> C -> B that is shorter than the direct path A -> B, AStar still uses the A -> B as an estimate. Because the estimate could be longer than the actual shortest path, the algorithm does not in these cases always find the shortest path.
