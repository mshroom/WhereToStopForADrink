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

[The actual performance](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/testing_document.md#route-algorithms) of Tsp Exact declines fast when a bigger graph is used - just like the time complexity suggests. With graph sizes 11-15, the time required to run the algorithm gets approximately 2-5 times longer when the graph size is increased by 1. The time complexity O(V!) suggests that the time would even grow factorially. If the algorithm could be tested with bigger input, it would therefore be expected that the performance would decline even more dramatically than with these small test graphs.

The tests also show that the complexity of the graph has a great impact on the performance of Tsp Exact: With a simple graph of 20 nodes the algorithm is much faster than with a complex graph of 15 nodes. If the shortest path is very straightforward, the branch-and-bound method can discard many branches effectively and thus find the path without going through all the possible permutations.

Another interesting result is that Tsp Exact is a little slower with graphs that use real data than with randomly created graphs. The reasons for this are discussed in the [testing document](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/testing_document.md#tests-made-with-graphs-using-real-imported-data). 

Tsp Nearest Neighbour is much faster: even with a graph of 2000 nodes it runs less than 1/10 seconds. On the other hand, the algorithm is not very clever. With simple graphs and graphs of less than 5 nodes it usually finds the shortest route, but with complex graphs of 12 or more nodes it is already very unlikely. With bigger graphs, the difference in the length of the optimal route and the route found by Tsp Nearest Neighbour also tends to get bigger. Thus the accuracy of Tsp Nearest Neighbour is poor when compared to Tsp Exact, which always finds the optimal route.

### Shortest path algorithms

There are not that radical differences between [the performace of the path algorithms](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/testing_document.md#path-algorithms). As suggested by the time complexity, Bfs is  the fastest of the three. As can be seen in the diagram behind the link above, the time required by Bfs grows almost in a linear way, when the graph gets bigger. The time required by A* or Dijkstra grows faster, but not nearly as steeply as was the case with Tsp Exact.

Dijkstra and A* both perform very similarly. With 6 different graphs, 5 graphs were solved in average faster by A* than by Dijkstra. Thus, it looks like the heuristics of A* are making it more effective than Dijkstra. However, with my test graphs the differences are so small (less than 50 milliseconds) that no big conclusions can be made.

Even though Bfs, Dijkstra and A* all find the shortest path in the normal case, there are some exceptions caused by faults or incoherence in the graph data. These are discussed in the [testing document](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/testing_document.md#algorithm-accuracy).
  
## Possible improvements
  
More algorithms could be added to the application. Especially another route algorithm would be useful, as Tsp Exact is impossible to use with bigger graphs, and Tsp Nearest Neighbour is in some ways too simple. A more intelligent approximation algorithm could probably be created, and anyway it would be more interesting to make comparisons if there was one more algorithm. One idea, which I had no time to implement, is to start creating the route with those nodes that are furthest away from all other nodes, trying to connect them to others with as short edges as possible.

The application itself would be more user friendly and useful if there was an interface to show the places on a map. It would also be useful to have a web app or a mobile app so that paths and routes could be found on the road. A mobile app would make it possible to set the home address according to the location of the mobile phone, not needing to give the address.

