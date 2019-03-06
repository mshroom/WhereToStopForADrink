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

