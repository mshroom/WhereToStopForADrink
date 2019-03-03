# Implementation document

## Basic idea

The application uses several algorithms to find the shortest path or route in a graph. The purpose of the project and the various algorithms are described in the [Project definition document](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/project_definition.md).

The application can create graphs based on real addresses in Helsinki region. It uses [Digitransit platform](https://digitransit.fi/en/developers/) to get all needed data. The [Address search](https://digitransit.fi/en/developers/apis/2-geocoding-api/address-search/) of the Geocoding API is used to get the coordinates for the addresses. The distances between each pair of coordinates are then calculated with the [Itinenary planning](https://digitransit.fi/en/developers/apis/1-routing-api/itinerary-planning/) of the Routing API.

## Application structure

Packages, classes and the most important connections between them are shown in the diagram below.

![UML diagram](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/architecture.png)

The application consists of six packages. The _**ui**_ package contains only the console ui. However, it would be possible to add another user interface later.

The classes in the _**control**_ package make up the core of the software logic. They create and maintain all needed data (graphs and places) and control and measure the performance of the algorithms. The algorithms themselves are implemented in the _**algorithms**_ package. Both the control and the algorithm classes need various data structures (heaps, queues and queueable objects), which are situated in the _**dataStructures**_ package.

The control classes and the ui use the services of the _**io**_ package, either for user input and system output or for reading and writing files. The control classes also use the services of the _**web**_ package for making http requests to the Digitransit server.

  
## Performance
  
The time and space complexities of the algorithms are described in the [Project definition document](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/project_definition.md). Below is a table summarizing the complexities for each algorithm.

| Algorithm | Time complexity | Space complexity |
|---|---|---|
| Tsp Exact | O(V!) | O(V²) |
| Tsp Nearest Neighbour | O(V²) | O(V) |
| Dijkstra | O((V + E) log V) | O(V) |
| A* | O((V + E) log V) | O(V) |
| Bfs | O((V + E) |  O(V) |

### Shortest route algorithms 

[The actual performance](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/testing_document.md#route-algorithms) of Tsp Exact declines fast when a bigger graph is used - just like the time complexity suggests. With graph sizes 11-15, the time required to run the algorithm almost doubles when the graph size is increased by 1. The time complexity O(V!) suggests that the time would not only double but grow factorially. If the algorithm could be tested with bigger input, it would therefore be expected that the performance would decline even more dramatically than with these small test graphs.

The tests also show that the complexity of the graph has a great impact on the performance of Tsp Exact: With a simple graph of 20 nodes the algorithm is much faster than with a complex graph of 15 nodes. If the shortest path is very straightforward, the branch-and-bound method can discard many branches effectively and thus find the path without going through all the possible permutations.

Tsp Nearest Neighbour is much faster: even with a graph of 2000 nodes it runs less than 1/10 seconds. On the other hand, the algorithm is not very clever and rarely finds the shortest route, unlike Tsp Exact which always finds the shortest route.
  
## Possible improvements
  
TODO
  
## Sources
  
  
