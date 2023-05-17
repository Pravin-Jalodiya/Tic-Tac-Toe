package com.example.pirateadventure;

import static android.text.TextUtils.isEmpty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class MainActivity extends AppCompatActivity {
    EditText et1,et2;
    MediaPlayer mysong;
    boolean playing = false;
    boolean manual = false;
    boolean auto = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mysong=MediaPlayer.create(MainActivity.this,R.raw.piratedaudioextended);

        et1 = findViewById(R.id.PersonName1);
        et2 = findViewById(R.id.PersonName2);
    }

    public void playit(View v){

        TextView t = findViewById(R.id.button2);

        if(!playing)
        {
            mysong.start();
            t.setText("Music ON");
            playing = true;
        }
        else
        {
            mysong.pause();
            t.setText("Music OFF");
            playing = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(auto==false) {
            TextView t = findViewById(R.id.button2);
            mysong.pause();
            t.setText("Music OFF");
            playing = false;
            manual = true;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        auto = false;
        if(manual==false) {
            mysong.start();
            TextView t = findViewById(R.id.button2);
            t.setText("Music ON");
            playing = true;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TextView t = findViewById(R.id.button2);
        mysong.release();
        t.setText("Music OFF");
        playing = false;

    }

    public void startgame(View view) {

        String player1name = et1.getText().toString();
        String player2name = et2.getText().toString();
        if(isEmpty(player1name) || isEmpty(player2name)) Toast.makeText(this,"Enter Names", Toast.LENGTH_SHORT).show();
        else {
            Intent i = new Intent(MainActivity.this, start_game.class);
            auto= true;
            manual = true;
            i.putExtra("player1name", player1name);
            i.putExtra("player2name", player2name);
            startActivity(i);
        }

    }
}