# Project definition

## Purpose

The purpose of the project is to examine various algorithms to find the shortest path or route in a graph.

The idea was inspired by the website [http://kallioon.fi/](http://kallioon.fi/) where you can randomly choose a route of 
three or more bars in the Kallio district. The application is great, but it does not take account of the distances between
bars or your own whereabouts while making the route. For lazy walkers, a feature that would minimize the need to walk 
would be nice.

Digitransit Platform (https://digitransit.fi/en/developers/) offers open data about locations and journeys in Helsinki region. 
Through this platform, it is possible to find out walking distances (or even travel times by public transport) between 
any two addresses. In my project, I will use the addresses of the bars in Kallio as an example. If it would prove to be 
problematic to get all necessary data through the platform or if I want to test the algorithms with a larger data set, 
I could also create a random graph with imaginary places and distances between them.

## Problems and algorithms

### Problem 1

The distances between each pair of bars are known. If a group of bars is selected, how can we find the shortest possible route 
that visits all bars and returns home?

This is in fact known as the travelling salesman problem. As it is an NP-hard problem, there are no effective algorithms 
to solve it in reasonable time except for very short routes. For this problem, there are two possibilities:

- An exact algorithm that will always return the shortest route but is impractical for even slightly larger data. 
However, an average bar tour is rarely longer than maybe 5 or 6 bars, so for this purpose an exact algorithm could be used. 
A branch-and-bound algorithm will in most cases be more effective than the brute-force method of trying all permutations.

  - Time complexity: O(n!) As there are that many permutations for the order of the vertices, it is the worst case even 
for a branch-and-bound algorithm
  - Space complexity: O(n²) There are n recursion levels, each with the space complexity of O(n)

- An approximation algorithm that will provide a quick solution that is not accurate but often good enough. 
A good example is the nearest neighbour algorithm that starts in one vertex and always continues to the closest 
unvisited vertex. However, in some cases the algorithm might give the worst possible route.

  - Time complexity: O(n²) Each vertex has an edge to all other vertices, so the time complexity of finding the closest vertex is O(n). This has to be done n times, once for each vertex.
  - Space complexity: O(n) Only the edges of one vertex must be handled at the same time.

### Problem 2

We want to go from one location to another by setting the maximum length of walk between two waypoints (bars). 
What is the shortest possible route and the minimum amount of bars that we have to visit?

Since there is a maximum length for the allowed distance between two bars, longer edges must be removed from the graph. 
After that, the problem can be solved with a pathfinding algorithm that can be modified to give the optimal solution 
based on the length of the path, or the number of vertices.

- Dijkstra's Algorithm offers an effective solution that always finds the shortest path. It uses a greedy strategy by always 
following a path that seems to be the best choice at the moment.

  - Time complexity: O((|V| + |E|) log |V|) if a min-priority queue is used when finding the next closest vertex.
  - Space complexity: O(|V|)
  - Datastructures: min-heap (priority queue)

- A* is an algorithm that adds heuristics to the logic of Dijkstra's Algorithm by estimating the distance to the goal 
for each vertex and preferring those with a shorter estimate. It gives an optimal result if the distances to the goal 
are never overestimated, which is true in my case.

  - Time and space complexity: Same as Dijkstra's, but is usually more effective.
  - Datastructures: min-heap (priority queue)

- If we want to find a path with a minumum amount of bars, the distances between vertices can be ignored and the path can be 
found with a bredth-first search algorithm.

  - Time complexity: O(|V| + |E|)
  - Space complexity: O(|V|)
  - Data structures: queue

## Sources

[https://en.wikipedia.org/wiki/Travelling_salesman_problem](https://en.wikipedia.org/wiki/Travelling_salesman_problem)

[https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm](https://en.wikipedia.org/wiki/Nearest_neighbour_algorithm)

[https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm](https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm)

[https://en.wikipedia.org/wiki/A*_search_algorithm](https://en.wikipedia.org/wiki/A*_search_algorithm)

Data structures and algorithms course material from autumn 2017



