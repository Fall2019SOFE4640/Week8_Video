package com.example.sofe4640.playvideo;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer player;
    private SurfaceView surfaceView;
    private SurfaceHolder holder;
    private int  currentposition ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        surfaceView = (SurfaceView) findViewById(R.id.surfaceView);
        holder = surfaceView.getHolder();
    }


    public void downloadAndPlayVideo(View view) {

        EditText editText = (EditText)findViewById(R.id.txtURL);
        String url = editText.getText().toString();

        if(player == null) {
            player = new MediaPlayer();
            player.setScreenOnWhilePlaying(true);

        }else{
            player.stop();
            player.reset();
        }
        try {
            player.setDataSource(url);
            player.setDisplay(holder);
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    int width = player.getVideoWidth();
                    int height = player.getVideoHeight();
                    if (width != 0 && height != 0) {
                        holder.setFixedSize(width, height);
                        player.start();
                    }
                }
            });
            player.prepareAsync();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pause(View v){
        if (player != null) {

            currentposition =  player.getCurrentPosition();
            player.pause();
        }
    }

    public void resume(View v){
        if (player != null) {
            //player.start();
            player.seekTo(currentposition);

        }
    }

    public void stop(View v){
        if (player != null) {
                player.stop();
                player.release();
        }
    }

}
