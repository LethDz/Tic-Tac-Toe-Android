package com.lethdz.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainScreen extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        textChangeColor();
    }

    public void startPlaying(View view) {
        Intent intent = new Intent(this, GameScreen.class);
        startActivity(intent);
        finish();
    }

    public void textChangeColor() {
        Thread threadText = new Thread("A thread changes text"){
            @Override
            public void run() {
                super.run();
                while (true) {
                    try {
                        TextView txtGameTitle = findViewById(R.id.txtGameTitle);
                        txtGameTitle.setTextColor(Color.RED);
                        Thread.sleep(1000);
                        txtGameTitle.setTextColor(Color.GREEN);
                        Thread.sleep(1000);
                        txtGameTitle.setTextColor(Color.BLUE);
                        Thread.sleep(1000);
                        txtGameTitle.setTextColor(Color.BLACK);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        threadText.start();
    }
}
