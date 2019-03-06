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

You can also just download the [jar file](TODO:link). Navigate to the folder where you placed the jar file and copy 
the folder [data](https://github.com/mshroom/WhereToStopForADrink/tree/master/data) there. The application will use 
this folder to access saved data. After that, run the command:

```
java -jar WhereToStopForADrink.jar
```

The folder *data* contains files needed to run tests (subfolder *testData*) and files that are used to save your
own data (subfolder *userData*). If you just downloaded the jar file, you won't need to run tests so you can delete the *testData* 
folder.

## Using the application

The application has a simple console user interface. In each menu, the application lists all possible 
commands. The application starts by opening the main menu. 

### Settings menu

In the settings menu, you can import and save data.

#### Creating a graph from a list of places

You can create a graph from a list of places in Helsinki region. You need a text file that contains 
the names and the addresses of the places. Each line in the text file must be in the following form: 

```
Name of the place;Address
```

For example:

```
5th Street Bar & Cafe;Viides linja 7 Helsinki
```

To create a graph from your file, give the command *new* and enter the name of the file. The file 
must be in the same folder with the jar file, or in a subfolder, in which case the whole path must 
be entered. For example, the *data/userData* subfolder contains the file *lessBars.txt* that has the 
addresses of 10 bars. You can import theses addresses by commanding *new* and 
*data/userData/lessBars.txt*.

Importing places from a text file will take some time, as the application will connect to the 
server in order to get the coordinates and distances for each place. I recommend to test the 
functionality first with a small file, for example the *lessBars.txt* file mentioned above.

#### Using a saved graph

Give the command *memory* to use a previously saved graph. Saved data is stored in the files 
*savedGraph.txt* and *savedPlaces.txt* that are in the *data/userData* folder. If you just 
downloaded this application, those files will contain the graph that consists of 83 bars 
in Kallio area.

#### Saving a graph

After you have created a graph, or modified a saved graph, you can save it by commanding *save*. 
This instruction saves the list of places to the file *savedPlaces.txt* and the graph to the 
file *savedGraph.txt* in the *data/userData* folder. At the moment, the application uses only 
these specific files to save data. Previously saved data will thus be overwritten.

#### Defining home address

When an imported custom graph is in use, the paths and routes will start at your home address. 
The default home address is Viides linja 11. You can change this by giving the command *home*, 
verifying your request with the command *yes* and entering the new address. The application 
will then connect to the server and get the coordinates and distances for the new home address. 
If this operation is successful, the home address will be changed. Otherwise an error message 
is given.

#### Adding a place to current graph

When an imported custom graph is in use, you can add a new place to the graph by giving the 
command *add*. Enter the name and the address of the place. The application will then connect 
to the server and get the coordinates and distances for the new place. If this operation is 
successful, the new place is added to the current graph. Otherwise an error message is given. 
Remember to save the graph if you want to add the place to the graph permanently.



