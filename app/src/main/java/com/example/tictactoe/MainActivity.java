package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // State Meanings
    // 0 - X
    // 1 - O
    // 2 - Null
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6},
                            {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};
    @SuppressLint("SetTextI18n")
    public void playerTouch(View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());

        // Text down the screen
        TextView status = findViewById(R.id.textView2);

        if (!gameActive) {
            gameReset(view);
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);

            // To check if the grid is full for checking draw condition
            boolean gridFull = false;
            for (int i : gameState) {
                if (i == 2) {
                    gridFull = false;
                    break;
                }
                else {
                    gridFull = true;
                }
            }

            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                if (gridFull) {
                    status.setText("");
                } else {
                    status.setText("O's Turn - Tap to play");
                }
                activePlayer = 1;
            }
            else {
                img.setImageResource(R.drawable.o);
                status.setText("X's Turn - Tap to play");
                activePlayer = 0;
            }
            img.animate().translationYBy(1000f).setDuration(10);
        }

        // Win conditions
        for (int[] winPos : winPositions) {
            String winnerStr;
            if (gameState[winPos[0]] == gameState[winPos[1]] &&
                    gameState[winPos[1]] == gameState[winPos[2]] &&
                    gameState[winPos[0]] != 2) {

                if (gameState[winPos[0]] == 0){
                    winnerStr = "Player X is the winner!";
                    gameActive = false;
                    resBtnOn(view);
                }
                else{
                    winnerStr = "Player O is the winner!";
                    gameActive = false;
                    resBtnOn(view);
                }
                status.setText(winnerStr);
                break;
            }
        }
        if ((status.getText().toString()).equals("")) {
            status.setText("Game Draw!");
            gameActive = false;
            resBtnOn(view);
        }
    }

    // Game reset function
    public void gameReset(View view) {
        gameActive = true;
        activePlayer = 0;
        Arrays.fill(gameState, 2);

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        // Setting buttons clickable
        (findViewById(R.id.imageView0)).setClickable(true);
        (findViewById(R.id.imageView1)).setClickable(true);
        (findViewById(R.id.imageView2)).setClickable(true);
        (findViewById(R.id.imageView3)).setClickable(true);
        (findViewById(R.id.imageView4)).setClickable(true);
        (findViewById(R.id.imageView5)).setClickable(true);
        (findViewById(R.id.imageView6)).setClickable(true);
        (findViewById(R.id.imageView7)).setClickable(true);
        (findViewById(R.id.imageView8)).setClickable(true);

        // Hiding restart button
        Button res = findViewById(R.id.restart);
        res.setVisibility(View.INVISIBLE);
    }

    public void resBtnOn(View view) {
        Button restartBtn = findViewById(R.id.restart);
        restartBtn.setVisibility(View.VISIBLE);

        // Freezing win page
        (findViewById(R.id.imageView0)).setClickable(false);
        (findViewById(R.id.imageView1)).setClickable(false);
        (findViewById(R.id.imageView2)).setClickable(false);
        (findViewById(R.id.imageView3)).setClickable(false);
        (findViewById(R.id.imageView4)).setClickable(false);
        (findViewById(R.id.imageView5)).setClickable(false);
        (findViewById(R.id.imageView6)).setClickable(false);
        (findViewById(R.id.imageView7)).setClickable(false);
        (findViewById(R.id.imageView8)).setClickable(false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}