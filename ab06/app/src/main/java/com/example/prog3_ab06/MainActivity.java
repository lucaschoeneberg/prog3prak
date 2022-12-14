package com.example.prog3_ab06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.example.prog3_ab06.helper.OnCellClickListener;
import com.example.prog3_ab06.logic.Game;
import com.example.prog3_ab06.ui.Cell;
import com.example.prog3_ab06.ui.ViewAdapter;
import android.view.Window;
import android.view.WindowManager;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    public static final long TIMER_LENGTH = 999000L;    // 999 seconds in milliseconds
    public static final int GRID_SIZE = 10;

    private RecyclerView recyclerView;
    private TextView restart, timer, flag, flagsLeft;
    private CountDownTimer countDownTimer;
    private Drawable flagbackground;
    private int secondsElapsed;
    private boolean timerStarted;
    private ViewAdapter viewAdapter;
    private Game game;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
        recyclerView = findViewById(R.id.activity_main_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 10));

        timer = findViewById(R.id.activity_main_timer);
        timerStarted = false;
        countDownTimer = new CountDownTimer(TIMER_LENGTH, 1000) {
            @SuppressLint("DefaultLocale")
            public void onTick(long millisUntilFinished) {
                secondsElapsed += 1;
                timer.setText(String.format("%03d", secondsElapsed));
            }

            public void onFinish() {
                game.outOfTime();
                Toast.makeText(getApplicationContext(), "Game Over: Timer Expired", Toast.LENGTH_SHORT).show();
                game.revealBombs();
                viewAdapter.setCells(game.getMineGrid().getGameGrid());
            }
        };

        flagsLeft = findViewById(R.id.activity_main_flagsleft);

        game = new Game(GRID_SIZE);
        flagsLeft.setText(String.format("%03d", 10 - game.getFlagCount()));
        viewAdapter = new ViewAdapter(game.getMineGrid().getGameGrid(), this);
        recyclerView.setAdapter(viewAdapter);

        restart = findViewById(R.id.activity_main_restart);
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game = new Game(GRID_SIZE);
                viewAdapter.setCells(game.getMineGrid().getGameGrid());
                timerStarted = false;
                countDownTimer.cancel();
                secondsElapsed = 0;
                timer.setText(R.string.default_count);
                flagsLeft.setText(String.format("%03d", 10 - game.getFlagCount()));
            }
        });

        flag = findViewById(R.id.activity_main_flag);
        flagbackground = flag.getBackground();
        flagbackground.setColorFilter(Color.parseColor("#454246"), PorterDuff.Mode.DST_OVER);
        flag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                game.toggleMode();
                if (game.isFlagMode()) {
                    flagbackground.setColorFilter(Color.parseColor("#D4D3CF"), PorterDuff.Mode.DST_OVER);
                } else {
                    flagbackground.setColorFilter(Color.parseColor("#454246"), PorterDuff.Mode.DST_OVER);
                }
            }
        });
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void cellClick(Cell cell) {
        game.handleCellClick(cell);

        flagsLeft.setText(String.format("%03d", 10 - game.getFlagCount()));

        if (!timerStarted) {
            countDownTimer.start();
            timerStarted = true;
        }

        if (game.isGameOver()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Over", Toast.LENGTH_SHORT).show();
            game.revealBombs();
        }

        if (game.isGameWon()) {
            countDownTimer.cancel();
            Toast.makeText(getApplicationContext(), "Game Won", Toast.LENGTH_SHORT).show();
            game.revealBombs();
        }

        viewAdapter.setCells(game.getMineGrid().getGameGrid());
    }
}