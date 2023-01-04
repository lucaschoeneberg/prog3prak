package com.example.swingolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragAndDropPermissions;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.swingolf.db.entity.player;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import com.example.swingolf.db.dao.*;
import com.example.swingolf.db.AppDatabase;

public class MainActivity extends AppCompatActivity {
    private AppDatabase database;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "swingolf_database2.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        TextView playerName = findViewById(R.id.addPlayerTextView);
        Button playerAdd = (Button) findViewById(R.id.addPlayer);
        Button goToField = (Button) findViewById(R.id.goToField);
        ChipGroup chipGroup = (ChipGroup) findViewById(R.id.chipGroup);

        for (player player : database.getPlayerDao().getAllPlayers()) {
            Chip chip = new Chip(chipGroup.getContext());
            chip.setText(player.getName());
            chip.setChipBackgroundColorResource(R.color.chipBackground);
            chip.setCloseIconVisible(true);
            chip.setTextAppearance(R.style.ChipTextAppearance);
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.getPlayerDao().deletePlayerById(player.getId());
                    chipGroup.removeView(chip);
                }
            });
            chipGroup.addView(chip);
        }

        playerAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                player _player = new player(playerName.getText().toString());
                database.getPlayerDao().insert(_player);
                Chip chip = new Chip(chipGroup.getContext());
                chip.setText(_player.getName());
                chip.setChipBackgroundColorResource(R.color.chipBackground);
                chip.setCloseIconVisible(true);
                chip.setTextAppearance(R.style.ChipTextAppearance);
                chip.setOnCloseIconClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        database.getPlayerDao().deletePlayerById(_player.getId());
                        chipGroup.removeView(chip);
                    }
                });
                chipGroup.addView(chip);
                playerName.setText("");
            }
        });

        goToField.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, match_attribute_selector.class);
                intent.putExtra("key", "awdawd");
                startActivity(intent);
            }
        });

        database.close();
    }

    @SuppressLint("SetTextI18n")
    private void createChip(ChipGroup chipGroup, player _player) {
        Chip chip = new Chip(chipGroup.getContext());
        chip.setText(_player.getId() + " " + _player.getName());
        chip.setChipBackgroundColorResource(R.color.chipBackground);
        chip.setCloseIconVisible(true);
        chip.setTextAppearance(R.style.ChipTextAppearance);
        chip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.getPlayerDao().deletePlayerByName(_player.getName());
                chipGroup.removeView(chip);
            }
        });
        chipGroup.addView(chip);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}