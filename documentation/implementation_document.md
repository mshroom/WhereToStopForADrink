# Implementation document

## Basic idea

The application uses several algorithms to find the shortest path or route in a graph. The purpose of the project and the various algorithms are described in the [Project definition document](https://github.com/mshroom/WhereToStopForADrink/blob/master/documentation/project_definition.md).

The application can create graphs based on real addresses in Helsinki region. It uses [Digitransit platform](https://digitransit.fi/en/developers/) to get all needed data. The [Address search](https://digitransit.fi/en/developers/apis/2-geocoding-api/address-search/) of the Geocoding API is used to get the coordinates for the addresses. The distances between each pair of coordinates are then calculated with the [Itinenary planning](https://digitransit.fi/en/developers/apis/1-routing-api/itinerary-planning/) of the Routing API.

## Application structure

TODO: UML/Package diagram

The application consists of six packages. The software logic is divided in 3 main packages:

- algorithms
  - Shortest path and shortest route algorithms
- dataStructures
  - Heaps, queues and queueable objects
- control
  - Classes that compare algorithms, generate and save graphs and communicate with web classes

In addition to these, there are 3 more packages:

- ui
  - Contains the text ui
- io
  - IO classes for console and files
- web
  - Classes that generate and send http requests
  
  ## Performance
  
  TODO compare and analyze performance of algorithms
  
  ## Possible improvements
  
  TODO
  
  ## Sources
  
  
