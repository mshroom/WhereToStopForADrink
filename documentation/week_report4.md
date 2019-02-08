# Week report 4

This week I managed to make my application get real data from Digitransit API. All that is needed is a text file containing names and addresses of places in Helsinki region. My application will then get the coordinates for the places and the distances between the coordinates through the API. The results are saved in a graph and a list of Place objects. As I am not very familiar with making http queries, all this took some effort. Now everything seems to work correctly.

The structure of my application is now getting more clear. I don't think that I will need much more classes. The ui will probably need a lot of refactoring. Right now there is a possibility to import and export data and to compare the algorithms. But the paths and routes are showed only as a list of indexes. I still have to add a feature to print the paths and routes so that the names and addresses of the places are shown in some pretty way.

I also configured Travis and Codecov to monitor my project (the badges are in the testing document). I noticed that Travis gives worse test coverage results than jacoco if I run it locally, so it looks like I need to write some more tests... My project structure needed some changes so that Travis could understand it. Right now the project root is a little mess, I have to do some cleaning. I also started writing testing and implementation documents.

Time spent: 20 hours.
