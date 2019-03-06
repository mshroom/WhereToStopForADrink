# User's guide

## Installing and starting the application

To run the application, do one of the following options:

### A) Downloading the whole project

Download the project to your computer. Open the console and navigate to the project root folder. 
To start the application, run the command:

```
java -jar WhereToStopForADrink.jar
```

### B) Downloading the jar file

You can also just download the [jar file](https://github.com/mshroom/WhereToStopForADrink/blob/master/WhereToStopForADrink.jar). Navigate to the folder where you placed the jar file and copy the folder [data](https://github.com/mshroom/WhereToStopForADrink/tree/master/data) there. The application will use this folder to access saved data. After that, run the command:

```
java -jar WhereToStopForADrink.jar
```

The folder *data* contains files needed to run tests (subfolder *testData*) and files that are used to save your own data (subfolder *userData*). If you just downloaded the jar file, you won't need to run tests so you can delete the *testData* folder.

## Using the application

The application has a simple console user interface. In each menu, the application lists all possible commands. The application starts by opening the main menu. 

### Settings menu

In the settings menu, you can import and save data.

#### Creating a graph from a list of places

You can create a graph from a list of places in Helsinki region. You need a text file that contains the names and the addresses of the places. Each line in the text file must be in the following form: 

```
Name of the place;Address
```

For example:

```
5th Street Bar & Cafe;Viides linja 7 Helsinki
```

To create a graph from your file, give the command *new* and enter the name of the file. The file must be in the same folder with the jar file, or in a subfolder, in which case the whole path must be entered. For example, the *data/userData* subfolder contains the file *lessBars.txt* that has the addresses of 10 bars. You can import theses addresses by commanding *new* and *data/userData/lessBars.txt*.

Importing places from a text file will take some time, as the application will connect to the server in order to get the coordinates and distances for each place. I recommend to test the functionality first with a small file, for example the *lessBars.txt* file mentioned above.

#### Using a saved graph

Give the command *memory* to use a previously saved graph. Saved data is stored in the files *savedGraph.txt* and *savedPlaces.txt* that are in the *data/userData* folder. If you just downloaded this application, those files will contain the graph that consists of 83 bars in Kallio area. Do not delete these files.

#### Saving a graph

After you have created a graph, or modified a saved graph, you can save it by commanding *save*. This instruction saves the list of places to the file *savedPlaces.txt* and the graph to the file *savedGraph.txt* in the *data/userData* folder. At the moment, the application uses only these specific files to save data. Previously saved data will thus be overwritten. Do not delete these files.

#### Defining home address

When an imported custom graph is in use, the paths and routes will start at your home address. The default home address is Viides linja 11. You can change this by giving the command *home*, verifying your request with the command *yes* and entering the new address. The application will then connect to the server and get the coordinates and distances for the new home address. If this operation is successful, the home address will be changed. Otherwise an error message is given.

#### Adding a place to current graph

When an imported custom graph is in use, you can add a new place to the graph by giving the command *add*. Enter the name and the address of the place. The application will then connect to the server and get the coordinates and distances for the new place. If this operation is successful, the new place is added to the current graph. Otherwise an error message is given. Remember to save the graph if you want to add the place to the graph permanently.

### Path menu

In the path menu you can compare shortest path algorithms. The shortest path algorithms try to find the shortest path from the starting node to the goal node.

#### Using ready-made test graphs

The easiest way to compare the performance of path algorithms is to use the ready-made test graphs. If you choose *simple*, you can choose either a *small* or a *big* test graph. After that, the application asks for an index. This is the index of the node that you want to find. The index of the starting node is 0.

You can also create a random graph of any size. If you give the command *random*, the application asks for the size of the graph. After that the random graph is created and the search is made from the starting node (index 0) to the goal node (the biggest index). 

The application then shows statistics for each algorithm. Statistics include the time elapsed, the length of the shortest path to the goal and the order of nodes (indexes) in the path.

#### Using an imported custom graph

You can make searches in the graph that you have imported by selecting *custom* and entering the index of your destination. If you do not know the index, you can search places by name or address by giving the command *find*. The results of the search are shown, after which you can choose the index of the place you want.

When you have given the index, you are asked to enter the maximum walking distance between two points. This means that if the distance between two places is longer than the maximum, you must stop somewhere in the middle. If the maximum distance is too short, there might not be any path to the destination. On the other hand, if the maximum distance is very long, there will always be a direct path from the home place to the destination.

After this, the search is made from home address to the destination, and the statistics are shown in the same way than with test graphs. If there is a path to the destination, you are able to print a more clear description of the path by choosing any of the algorithms; all algorithms might have found a different path.

### Route menu

In the route menu you can compare shortest route algorithms. Shortest route algorithms try to find the shortest route that visits all nodes in the graph and returns to the starting node.

#### Using ready-made test graphs

The easiest way to compare the performance of route algorithms is to use the ready-made test graphs. If you choose *simple*, you can choose either a *small* or a *big* test graph. You can also create a random graph of any size by giving the command *random* and entering the size of the graph. 

After that the search is made from the starting node (index 0) through the graph and back to the starting node.If the random graph has more than 17 nodes, only the approximation algorithm will be tested, because the exact algorithm is too slow.

The application then shows statistics for each algorithm. Statistics include the time elapsed, the length of the shortest route and the order of nodes (indexes) in the route.

#### Using an imported custom graph

You can make searches in the graph that you have imported by selecting *custom* and entering the number of places that you want to visit. The application then randomly chooses that many places in your current graph and finds the route that visits all those places. With more than 15 places, only the approximation algorithm will be tested, because the exact algorithm is too slow.

After this, the statistics are shown in the same way than with test graphs. You are then able to print a more clear description of the route by choosing any of the algorithms; all algorithms might have found a different route.

### Quit the application

Quit the application by giving the command *quit* in any menu.
