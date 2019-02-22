# Week report 6

This last week I added some features to my application, including changing home address and adding and saving a new place.
I also made the testing of route algorithms more interesting by making the application choose places by random. 
Another improvement is that the user can now search places in the custom graph by name or address.

I also did small fixes and refactoring, concentrating on those issues that were mentioned in the first peer review. That 
included combining the integer and object queues to a general type queue, and adding some more complex test graphs.

In addition to that, I have been writing more tests, especially for the Ui class. In the same time I have been updating 
the testing document. The bigger test graphs that I created were helpful in performance testing. I tried to make even bigger 
graphs for pathfinding algorithms, but the program ran out of memory when I tried to run the tests with them...

It would be nice if I had some way to visualize the graphs, which was also mentioned in a peer review. 
However, I'm not sure what kind of representation would be good, since my graphs are not labyrinth-like but based on a
distance matrix. I have been thinking that if I were to continue this project, I would make a graphical interface that 
would show the places on a map. However, that would probably require more time than I have left now.

Time spent: 15 hours
