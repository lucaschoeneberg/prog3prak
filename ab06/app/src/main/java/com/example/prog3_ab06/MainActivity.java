package com.example.prog3_ab06;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.prog3_ab06.helper.OnCellClickListener;
import com.example.prog3_ab06.logic.Game;
import com.example.prog3_ab06.ui.Cell;
import com.example.prog3_ab06.ui.ViewAdapter;

import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements OnCellClickListener {
    RecyclerView recyclerView;
    ViewAdapter viewAdapter;
    Game game;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.activity_main_grid);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 10));
        game = new Game(10);
        recyclerView.setAdapter(viewAdapter);
    }

    @Override
    public void cellClick(Cell cell) {
        game.handleCellClick(cell);
    }
}