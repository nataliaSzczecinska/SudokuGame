package com.sudoku.io;

import com.sudoku.structure.Coordinates;
import com.sudoku.structure.SudokuBoard;

public class TextFactor {
    public static String cloneException() {
        return "The clone is failed";
    }

    public static String elementDoNotExist(String text) {
        return "The " + text + " do not exist";
    }

    public static String start() {
        return "WELCOME!" +
                "\nLET'S START THE GAME!" +
                "\nThe aim is to put into empty places the number between 1 to 9." +
                "\nTo do that you have to write three numbers one after other, like this:" +
                "\ncolumn number, row number, the number to add, for example" +
                "\n1,2,3 means that in first column and second row put number 3" +
                "\nWhen you finish press 's' - solve, and computer check the solution of your game" +
                "\nIf you want exit the game press 'e' - end game" +
                "\nIf you want play again press 'p' - play again";
    }

    public static String mistakeChoice() {
        return "Ups... Something is wrong. You chose wrong options. Read the instruction again";
    }

    public static String playerOption() {
        return "Your choice: ";
    }

    public static String endGame() {
        return "Thank you for game";
    }

    public static String hasBeenSolved() {
        return "The game has been already solved. Choose other option ";
    }

    public static String cannotPutNumberIntoBoard(Coordinates coordinates) {
        return "Coordinates " + coordinates + " cannot be put in to the board element because on of the reason:" +
                "\n* in the element there is other number " +
                "\n* in row/column or box there is the same value";
    }

    public static String problemWithMethod(String text) {
        return "There is a problem with method " + text + ", please try again";
    }

    public static String notEnoughBoardElements() {
        return "Sorry, the possible solution of the sudoku is to huge to resolve it\n" +
                " Please, put more numbers into board ";
    }

    public static String gameIsSolve(SudokuBoard board) {
        return "THE SOLVE SUDOKU BOARD\n" + board;
    }

    public static String cannotBeSolve() {
        return "Sorry, the sudoku board cannot be solve";
    }
}
