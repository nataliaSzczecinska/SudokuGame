# SudokuGame
Application to solve sudoku game

## General Information
The program is resolving the popular game sudoku if it is possible.

## Screenshots and game description
The game is started with following information
![Algorithm](src/main/resources/screenshots/start.png)

You can put number according to instruction
![Algorithm](src/main/resources/screenshots/putNumber.png)

Additionaly, you can put more than one sudoku element coordinates.
![Algorithm](src/main/resources/screenshots/putListNumber.png)

If you choose 's', there is three options:
* If you put less then 10 elements, the program will not start resolving. You will be asked to put more elements.
![Algorithm](src/main/resources/screenshots/solve1.png)

* If everything is ok, the program will resolved the sudoku and show the resolved board.
![Algorithm](src/main/resources/screenshots/solve2-1.png)
![Algorithm](src/main/resources/screenshots/solve2-2.png)

* If the sudoku has already solved and you will try to resolve it again, the program will asked you to choose other option.
![Algorithm](src/main/resources/screenshots/solve3.png)

If you choose 'p', the program will start again with empty board (it is not show an empty board).
![Algorithm](src/main/resources/screenshots/playAgain.png)

If you choose 'e', the program will thank you for playing and swich off.
![Algorithm](src/main/resources/screenshots/endGame.png)

 
## Technologies
JAVA version 11.0.9
 
## Setup
Gradle -> Tasks -> application -> run
 
## Licence
Freeware
