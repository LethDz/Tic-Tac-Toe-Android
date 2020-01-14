package com.lethdz.tictactoe;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainScreen extends Activity {
    MediaPlayer mpPlayer;
    private static boolean isPlaying = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        textChangeColor();
        mpPlayer = MediaPlayer.create(this, R.raw.menu);
        mpPlayer.setLooping(true);
        mpPlayer.start();
    }

    public void startPlaying(View view) {
        Intent intent = new Intent(this, GameScreen.class);
        mpPlayer.stop();
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mpPlayer.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isPlaying) {
            mpPlayer.start();
        }
    }

    public void stopMusic(View view) {
        Drawable iconChange;
        Button btnStopMusic = findViewById(R.id.btnStopMusic);
        if (mpPlayer.isPlaying()) {
            mpPlayer.pause();
            isPlaying = false;
            iconChange = getResources().getDrawable(android.R.drawable.ic_media_play, null);
            btnStopMusic.setBackground(iconChange);
        } else {
            mpPlayer.start();
            isPlaying = true;
            iconChange = getResources().getDrawable(android.R.drawable.ic_media_pause, null);
            btnStopMusic.setBackground(iconChange);
        }
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
                        txtGameTitle.setTextColor(Color.WHITE);
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
