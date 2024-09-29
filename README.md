# Project: The Parking Jam Game

## Authors

This project has been implemented by **Iván Moratalla Rivera**,  **David Morilla Sorlí**, **Daniel Pina Rica** and **Fernando Prados Vicente**.

## Description

The goal of this project is the development of the Parking Jam game using a Java program. This project uses the model-view-controller (MVC) design pattern. Parking Jam is a sliding blocks puzzle game inspired by Klotski, a classic game from the early 20th century. The objective of the game is to move the vehicles in a parking lot to allow the red car to exit. This README provides an overview of the game's features, installation instructions, and how to play.

## Features

- **Multiple Levels**: The game includes several levels, each with a unique board layout.
- **Board Elements**: The board is divided into squares containing walls, vehicles, the red car, and the parking exit.
- **Vehicle Movement**: Vehicles can only move forward or backward, not laterally.
- **Automatic Level Detection**: The game automatically detects when the red car has exited the parking lot and allows the user to advance to the next level.
- **Level Files**: Levels are read from plain text files named `levelN.txt` (where `N` is a positive number).
- **Error Handling**: The game checks for level correctness and shows an error message if a level is invalid.
- **Scoring**: The game tracks the number of moves made and provides a total score for all levels.
- **Undo and Save**: Players can undo moves, save their progress, and load saved games.
- **Game Menu**: Options to start a new game, restart the current level, undo the last move, save the game, open a saved game, and close the application.

## Interface Description

The `ControllerInterface` interface provides the following public methods:
### `public void startNewGame()`

  Initializes a new game.

- **Exceptions:**
  - `IllegalExitsNumberException`: When there's an illegal number of exits (there can only be one exit).
  - `IllegalCarDimensionException `: When a car dimensions are illegal (different from 1xN or Nx1, Red car only 2x1 or 1x2).
  - `NullBoardException` : when the board is null.

### `public void loadNewLevel()`

Loads a new level in the game.

- **Exceptions:**
  - `IllegalExitsNumberException`: When there's an illegal number of exits (there can only be one exit).
  - `IllegalCarDimensionException `: When a car dimensions are illegal (different from 1xN or Nx1, Red car only 2x1 or 1x2).
  - `NullBoardException` : when the board is null.

### `public int loadSavedLevel()`

Loads a saved level in the game and returns the number of the loaded level.

- **Exceptions:**
  - `IllegalExitsNumberException`: When there's an illegal number of exits (there can only be one exit).
  - `IllegalCarDimensionException `: When a car dimensions are illegal (different from 1xN or Nx1, red car only 2x1 or 1x2).
  - `NullBoardException` : when the board is null.

### `public char[][] moveCar(char car, int length, char way)`

Moves a car on the board and returns the updated board state after the move.

- **Parameters:**
  - `car ` (char): Specifies the unique identifier of the car to move.
  - `length ` (int): Specifies the length to move the car.
  - `way` (char): Specifies the direction to move the car.
- **Exceptions:**
  - `IllegalDirectionException`: if the direction specified is not valid. 
  - `LevelAlreadyFinishedException`: if the user attempts to make a movement after the level has finished.
  - `NullBoardException`: if the board is null.
  - `MovementOutOfBoundariesException`: if the movement attempted is out of boundaries.
  - `InvalidMovementException`: if the movement is invalid.
  - `IllegalCarException`: if the car does not exist.

### `public char[][] undoMovement()`

Undoes the last car movement done and returns the updated board state after undoing the movement.

- **Exceptions:**
  - `CannotUndoMovementException` : When there are no movements to be undone.
  - `IllegalDirectionException`: if the direction specified is not valid. 
  - `LevelAlreadyFinishedException`: if the user attempts to make a movement after the level has finished.
  - `NullBoardException`: if the board is null.
  - `MovementOutOfBoundariesException`: if the movement attempted is out of boundaries.
  - `InvalidMovementException`: if the movement is invalid.
  - `IllegalCarException`: if the car does not exist.

### `public char[][] getBoard()`

Returns the current state of the board.

### `public Pair<Integer, Integer> getBoardDimensions()`

Returns a pair containing the width and height of the board.

### `public Map<Character,Car> getCars()`

Returns a map of car identifiers mapped to car instances.

### `public int getGameScore()`

Returns the total game score.

### `public int getLevelScore()`

Returns the current level score.

### `public int getLevelNumber()`

Returns the current level number.

### `public IGrid getGrid()`

Returns the current grid.

### `public void setCars()`

Sets the car list on the grid.

### `public void setBoard(char[][] newBoard)`

Sets the board on the grid.

- **Parameters:**
  - `newBoard`: the new board to be set.

### `public void resetLevel()`

Resets the current level.

- **Exceptions:**
  - `NullBoardException`: if the board is null.

### `public boolean isMoveValid(char car, Coordinates newCoord, char way)`

Checks if a car movement is valid.It returns true if the movement is valid, false otherwise.

- **Parameters:**
  - `car ` (char): Specifies the unique identifier of the car to move.
  - `newCoord  ` (Coordinates): Specifies the new coordinates for the car.
  - `way` (char): Specifies the direction to move the car.

### `public boolean isLevelFinished()`

Checks if the current level is finished. It returns true if the level is finished, false otherwise.

### `public void setPunctuation(int score)`

Sets the score for the current level.

- **Parameters:**
  - `score ` (int): Specifies the score to set for the current level.

### `public void saveGame()`

Saves the current game state.

### `public void setGameScore(int totalPoints)`

Sets the total game score.

- **Parameters:**
  - `totalPoints` (int): Specifies the total points to set as the game score.

### `public void resetOriginalLevel()`

Resets the level to its original state.

- **Exceptions:**
  - `NullBoardException`: if the board is null.

### `public void loadLevel(int levelNumber)`

Loads a specific level by its number.

- **Parameters:**
  - `levelNumber` (int): Specifies the number of the level to load.

- **Exceptions:**
  - `NullBoardException` : when the board is null.
***

## Installation

1. Clone the repository using the following command or download the source code.
   ```bash
    git clone [project_repository_url]
   ```
2. Go to the parking-jam directory using the following command.
   ```bash
   cd parking-jam
   ```
3. Compile the source code and execute the game using the following commands:
   ```bash
   mvn compile
   mvn exec:java
   ```
## Running test suite

To execute the test suite for the project, follow these steps:
1. Clone the repository using the following command or download the source code.
   ```bash
    git clone [project_repository_url]
   ```
2. Go to the parking-jam directory using the following command.
   ```bash
   cd parking-jam
   ```
3. Finally, run the tests using the following command.
    ``` bash
    mvn test
    ```
## Level Files

The level files must be located inside the `parking-jam/src/main/resources/` levels folder.
The level files must be named `levelN.txt` (where `N` is a positive number).

### Format

- First Line: Level + number of the level
- Second Line: Dimensions of the board (nRows nColumns)
- Following Lines: Board elements, with each line containing nColumns characters representing:
    - '+' : Wall
    - ' ' : Empty square
    - [a-z] : Vehicle identifiers
    - '*' : Red car
    - '@' : Exit

### Example level

   ```txt
    Level 1
    8 8
    ++++++++
    +aabbbc+
    +  *  c+
    +d *   +
    +d fff +
    +de    +
    + e ggg+
    ++++@+++
   ```

## Other input files

The program uses other input files for playing sounds and showing images. These images are not to be modified. They are located inside the folders:
  1. `parking-jam/src/main/resources/cars` folder: Contains the images for the cars.
  2. `parking-jam/src/main/resources/exits` folder: Contains the images for the exits.
  3. `parking-jam/src/main/resources` folder: Contains the rest of resources like sounds or background images.
    
## Output files format

The program generates three files every time a game is saved.
These files are inside the folder `parking-jam/src/main/resources/savedGame`. Here are some brief descriptions of each file.

  1. `history.txt`: It contains the movements saved since the start of the level. It saves the movements in the format `car length direction`, where the direction is the opposite to the one 	made. All movemnts are written in separate lines. Here is an example file after moving car 'c' 2 units left, 	car 'b' 1 unit up and car 'a' 3 units down.	

   ```txt
   c 2 R
   b 1 D
   a 3 U
   ```
		 
  2. `level.txt`: It contains the level name, dimensions and board configuration at the moment of saving the game. It uses the same exact format that is used in the level files.
  3. `punctuation.txt`: It's made of two lines, the first one containing a number indicating the total score and the second one containing the level score at the moment of saving the game. For example, if the total score is 15 and the level score is 4, the content of the file would look like this:

   ```txt
   15
   4
   ``` 
## Controls to use the application

Once the game is run, it opens the next window:

<img src="readmeImages/main_window.png" width="25%" height="25%">

In this window it's possible to see three buttons:
  1. **NEW GAME**: By clicking it, it allows the user to start a new game, loading the first level.
  2. **LOAD LAST GAME**: By clicking it, it allows the user to load the last saved game.
  3. **SELECT LEVEL**: By clicking it, it allows the user to select the specific level they want to play. It's not allowed to save a game when entering in it through the select level button. it's only possible to do so when the **NEW GAME** button is clicked.

The level selection window looks like this:

<img src="readmeImages/level_selection_window.png" width="25%" height="25%">

The user can then select any available level or go back to the main menu.

When a level is started, it'll show the next window:

<img src="readmeImages/level_window.png" width="25%" height="25%">

The user can now start playing the game by clicking on any car and dragging it to their desired position. The allowed movements are only backward and forward and it's not possible to move the car laterally or diagonally.
At any time, the user can choose to press any of the buttons located right under the board.
  1. **UNDO**: By clicking it, it allows the user to undo the last recorded movement.
  2. **RESET LEVEL**: By clicking it, it allows the user to reset the level, going back to its initial configuration.
  3. **SAVE GAME**: By clicking it, it allows the user to save their game progress for later.
  4. **NEXT LEVEL**: This button only works when a level is finished, this is whenever the red car exits the board and the 'LEVEL COMPLETED' message is displayed. Clicking this button allows the user to advance to the next level.
  5. **BACK TO MAIN MENU**: By clicking it, it allows the user to go back to the main menu.

Once all the levels have been completed, the game will show the user a congratulations message indicating that they have finished the game.

## Requirements

- Java Development Kit (JDK) 8 or higher

## Dependencies

This project requires JUnit 5.6 for testing purposes, Java Swing for the visuals and SLF4J for logging.
