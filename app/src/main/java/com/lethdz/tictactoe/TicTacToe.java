package com.lethdz.tictactoe;

import java.util.Arrays;

public class TicTacToe {
    private static final String gameWin = " is win!!! Congratulation";
    private static final String gameDraw = "Game Draw!!! Try Again";
    enum gameState{Blank, X, Y};
    private int typeOfGame = 3;
    private gameState[][] gameBoard = new gameState[typeOfGame][typeOfGame];
    private int moveCount = 0;
    private String gameNotification = gameWin;
    private boolean gameOver = false;

    public TicTacToe(int typeOfGame) {
        this.typeOfGame = typeOfGame;
    }

    public TicTacToe() {
        fillGameBoard();
    }

    private void fillGameBoard() {
        for (int i = 0; i < typeOfGame; i++) {
            Arrays.fill(gameBoard[i], gameState.Blank);
        }
    }

    public void move(int x, int y, gameState state) {
        if (gameBoard[x][y] == gameState.Blank) {
            gameBoard[x][y] = state;
            moveCount++;
        }

        System.out.println(state + " " + "X: " + x + " Y: " +y);

        //Check Row Win Condition
        for (int i = 0; i < typeOfGame ; i++) {
            if (gameBoard[x][i] != state) {
                break;
            }
            if (i == (typeOfGame - 1)) {
                gameNotification = state.toString() + gameNotification;
                System.out.println(gameNotification + "Row");
                gameOver = true;
            }
        }

        //Check Column Win Condition
        for (int i = 0; i < typeOfGame; i++) {
            if (gameBoard[i][y] != state) {
                break;
            }
            if (i == (typeOfGame - 1)) {
                gameNotification = state.toString() + gameNotification;
                System.out.println(gameNotification + "Column");
                gameOver = true;
            }
        }

        //Check Diagonal Win Condition
        if (x == y) {
            for (int i = 0; i < typeOfGame; i++) {
                if (gameBoard[i][i] != state) {
                    break;
                }
                if (i == (typeOfGame - 1)) {
                    gameNotification = state.toString() + gameNotification;
                    System.out.println(gameNotification + "Diagonal");
                    gameOver = true;
                }
            }
        }

        //Check Anti-Diagonal Win Condition
        if (x + y == typeOfGame - 1) {
            for (int i = 0; i < typeOfGame; i++) {
                if (gameBoard[i][typeOfGame - (1+i)] != state) {
                    break;
                }
                if (i == (typeOfGame - 1)) {
                    gameNotification = state.toString() + gameNotification;
                    System.out.println(gameNotification + "Anti-Diagonal");
                    gameOver = true;
                }
            }
        }

        //Check Draw
        if (moveCount == (Math.pow(typeOfGame, 2) - 1) && !gameOver) {
            gameNotification = gameDraw;
            gameOver = true;
        }
    }

    public void restartGame() {
        gameBoard = new gameState[typeOfGame][typeOfGame];
        fillGameBoard();
        gameOver = false;
        moveCount = 0;
        gameNotification = gameWin;
    }

    public String getGameNotification() {
        return gameNotification;
    }

    public boolean isGameOver() {
        return gameOver;
    }
}
