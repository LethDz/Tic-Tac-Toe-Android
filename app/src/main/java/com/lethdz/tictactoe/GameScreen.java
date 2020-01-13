package com.lethdz.tictactoe;

import android.app.Activity;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameScreen extends Activity {
    TicTacToe game = new TicTacToe();
    private int playerTurn = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
    }

    public void playTicTacToe(View view) {
        printXorO(view.getId(), view);
    }

    public void restart(View view) {
        finish();
        startActivity(getIntent());
    }

    public void cancel(View view) {
        Intent intentMainScreen = new Intent(this, MainScreen.class);
        startActivity(intentMainScreen);
        finish();
    }

    public void printXorO (int btnId, View view) {
        Button buttonTicked = findViewById(btnId);
        TextView txtPlayerTurn = findViewById(R.id.txtPlayerTurn);
        if (buttonTicked.getText().toString() == "" && !game.isGameOver()) {
            if (playerTurn == 1) {
                buttonTicked.setText("X");
                buttonTicked.setTextColor(Color.BLUE);
                txtPlayerTurn.setText("O");
                txtPlayerTurn.setTextColor(Color.RED);
                checkMove(view);
                playerTurn = 2;
            } else {
                buttonTicked.setText("O");
                buttonTicked.setTextColor(Color.RED);
                txtPlayerTurn.setText("X");
                txtPlayerTurn.setTextColor(Color.BLUE);
                checkMove(view);
                playerTurn = 1;
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentMainScreen = new Intent(this, MainScreen.class);
        startActivity(intentMainScreen);
        finish();
    }

    public void checkMove (View view) {
        switch (view.getId()) {
            case R.id.btn00:
                game.move(0,0, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn01:
                game.move(0,1, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn02:
                game.move(0,2, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn10:
                game.move(1,0, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn11:
                game.move(1,1, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn12:
                game.move(1,2, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn20:
                game.move(2,0, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn21:
                game.move(2,1, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
            case R.id.btn22:
                game.move(2,2, playerTurn == 1
                        ? TicTacToe.gameState.X
                        : TicTacToe.gameState.Y);
                gameOver();
                break;
        }
    }

    private void alertGameOver() {
        DialogFragment gameOverNotification = new GameOverNotification();
        gameOverNotification.show(getFragmentManager(), "gameover");
    }

    private void gameOver() {
        if (game.isGameOver()) {
            SharedPreferences sharedPreferences = getSharedPreferences("GameInformation", Context.MODE_PRIVATE);
            TextView txtTurnTitle = findViewById(R.id.txtTurnTitle);
            TextView txtPlayerTurn = findViewById(R.id.txtPlayerTurn);
            savedInformation();
            alertGameOver();
            txtTurnTitle.setText(sharedPreferences.getString("GameNotification", ""));
            txtPlayerTurn.setText("");
        }
    }

    private void savedInformation() {
        SharedPreferences sharedPreferences = getSharedPreferences("GameInformation", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("GameNotification", game.getGameNotification());
        editor.apply();
    }
}
