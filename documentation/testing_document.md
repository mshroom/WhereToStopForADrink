# Testing document

## Travis status

[![Build Status](https://travis-ci.org/mshroom/WhereToStopForADrink.svg?branch=master)](https://travis-ci.org/mshroom/WhereToStopForADrink)

## Codecov test coverage

[![codecov](https://codecov.io/gh/mshroom/WhereToStopForADrink/branch/master/graph/badge.svg)](https://codecov.io/gh/mshroom/WhereToStopForADrink)

## Unit tests

Unit test coverage for the project is good, except for the ui and web packages that, at the moment, have no automatic tests. One reason for that is that there will probably still be big changes to the text ui design (the texts to be printed etc). The classes in the web package, on the other hand, are not very suitable for Unit testing because they require web connection and the responses to the http requests might vary from time to time, depending on the server. These classes will maybe be excluded from the test coverage reports.

## Manual tests

### Web package

The classes in the web package have been tested manually by sending http requests to the server (Digitransit) and checking that the returned data is valid. Experiments were made first with 10 addresses. The results could be validated by comparing them with requests made by browser and Digitransit's GraphiQL user interface.

The address search of the Geocoding API seems to function reliably. However, if the addresses are not formatted correctly, the server will sometimes give correct and sometimes completely wrong coordinates. Using URLEncoder helps.

The itinerary planning of the Routing API works also well for finding distances between coordinates. It turns out, that in some cases the server does not find the route or the distance. This happens sometimes if the two places are too close together - at least I suspect that to be the reason. The problem is quite easy to manage. When the distance between two places is not found, my application will create a graph where there is no edge between them. 

### User interface

TODO

## Performance

