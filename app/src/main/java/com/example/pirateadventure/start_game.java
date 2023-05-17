package com.example.pirateadventure;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class start_game extends AppCompatActivity {

    int player = 1; //player1 is ring
    int starter = 1;
    boolean isWinner = false;
    int imageclicked = -1;
    int [][]winningstates = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int []gamestate = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    public void load(View view){
        TextView t = findViewById(R.id.turn);

        String p1 = getIntent().getStringExtra("player1name");

        String p2 = getIntent().getStringExtra("player2name");


        ImageView v = (ImageView) view;

        int tag = Integer.parseInt(v.getTag().toString());

        imageclicked = gamestate[tag];

        if(isWinner==false && imageclicked==-1) {

            if (player == 1) {
                v.setImageResource(R.drawable.ring);
                gamestate[tag] = player;
                player = 2;
                t.setText(p2 + "'s turn");
            } else {
                v.setImageResource(R.drawable.bones6);
                gamestate[tag] = player;
                player = 1;
                t.setText(p1 + "'s turn");
            }

            for (int i = 0; i < winningstates.length; i++) {
                if (gamestate[winningstates[i][0]] == gamestate[winningstates[i][1]] && gamestate[winningstates[i][1]] == gamestate[winningstates[i][2]] && gamestate[winningstates[i][0]] > -1) {
                    String winner;
                    if (player == 2) winner = p1;
                    else winner = p2;
                    t.setText(winner + " WON");
                    t.setTextColor(0XFFFFFFFF);
                    if(player==2)
                    t.setBackgroundColor(0XFF770000);
                    else t.setBackgroundColor(0XFF000277);

                    isWinner = true;
                }
                else if(i==winningstates.length-1 && isWinner== false){
                    boolean draw = true;
                    for(int j = 0; j < gamestate.length; j++)
                    {
                        if(gamestate[j]==-1) draw = false;
                    }
                    if(draw==true) {
                        t.setText("GAME DRAW");
                        t.setTextColor(0XFFFFFFFF);
                        t.setBackgroundColor(0XFF000000);
                    }
                }
            }
        }
    }

    public void reset(View view){
        GridLayout gridLayout = findViewById(R.id.grid);
        int total_img = gridLayout.getChildCount();
        for(int i = 0;i < total_img; i++){
            ImageView v = (ImageView) gridLayout.getChildAt(i);
            v.setImageDrawable(null);
        }
        isWinner = false;
        imageclicked = -1;
        for(int j = 0; j < gamestate.length; j++){
            gamestate[j]=-1;
        }
        TextView t = findViewById(R.id.turn);

        t.setTextColor(0XFFFFFFFF);
        t.setBackgroundColor(0XDC000000);

        String p1 = getIntent().getStringExtra("player1name");

        String p2 = getIntent().getStringExtra("player2name");

        if(starter==1)
        {
            t.setText(p2+"'s turn");
            starter=2;
            player = 2;
        }
        else if(starter==2){
            t.setText(p1+"'s turn");
            starter=1;
            player = 1;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        TextView t = findViewById(R.id.turn);

        String play1name = getIntent().getStringExtra("player1name");

        String play2name = getIntent().getStringExtra("player2name");

        t.setText(play1name+"'s turn");

        TextView p1name = findViewById(R.id.p1name);

        TextView p2name = findViewById(R.id.p2name);

        p1name.setText(play1name);

        p2name.setText(play2name);

    }
}