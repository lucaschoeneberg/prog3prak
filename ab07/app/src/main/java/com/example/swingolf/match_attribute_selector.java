package com.example.swingolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.swingolf.db.AppDatabase;
import com.example.swingolf.db.entity.match;
import com.example.swingolf.db.entity.player;
import com.example.swingolf.db.entity.field;
import com.example.swingolf.db.entity.matchPlayerJoin;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.List;

public class match_attribute_selector extends AppCompatActivity {
    AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_attribute_selector);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "swingolf_database2.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();


        TextView tbxMatchName = findViewById(R.id.tbxMatchName);
        Button btnCreateMatch = findViewById(R.id.btnStartMatch);
        Spinner spnNumberOfHoles = findViewById(R.id.spnNumberOfHoles);
        Spinner spnNumberOfHitsPerHole = findViewById(R.id.spnNumberOfHitsPerHole);
        AutoCompleteTextView actxFieldName = findViewById(R.id.actxFieldName);
        ChipGroup chipGroup = findViewById(R.id.chipGroup);

        for (com.example.swingolf.db.entity.player player : database.getPlayerDao().getAllPlayers()) {
            Chip chip = new Chip(chipGroup.getContext());
            chip.setText(player.getName());
            chip.setChipBackgroundColorResource(R.color.chipBackground);
            chip.setCheckable(true);
            chip.setCloseIconVisible(false);
            chip.setTextAppearance(R.style.ChipTextAppearance);
            chip.setOnCloseIconClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    database.getPlayerDao().deletePlayerById(player.getId());
                    chipGroup.removeView(chip);
                }
            });
            chip.setOnCheckedChangeListener((buttonView, isChecked) -> {
                player.setPlaying(isChecked);
                database.getPlayerDao().update(player);
            });
            chipGroup.addView(chip);
        }

        btnCreateMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tbxMatchName.getText().toString().isEmpty()) {
                    tbxMatchName.setError("Match name is required");
                    return;
                }

                if (actxFieldName.getText().toString().isEmpty()) {
                    actxFieldName.setError("Field name is required");
                    return;
                }

                field _field = null;
                List<field> fileds = database.getFieldDao().getAllFields();
                for (field field : fileds) {
                    if (field.getName().equals(actxFieldName.getText().toString())) {
                        _field = field;
                    }
                }

                if (_field == null) {
                    _field = new field(actxFieldName.getText().toString(), spnNumberOfHoles.getSelectedItemPosition());
                    database.getFieldDao().insert(_field);
                    _field = database.getFieldDao().getAllFields().get(database.getFieldDao().getAllFields().size() - 1);
                }

                match _match = new match(tbxMatchName.getText().toString(), _field.getId(), String.valueOf(System.currentTimeMillis()), spnNumberOfHitsPerHole.getSelectedItemPosition());
                database.getMatchDao().insert(_match);
                _match = database.getMatchDao().getAllMatches().get(database.getMatchDao().getAllMatches().size() - 1);
                for (player _player : database.getPlayerDao().getAllPlayers()) {
                    if (_player.isPlaying()) {
                        matchPlayerJoin matchPlayerJoin = new matchPlayerJoin(_match.getId(), _player.getId());
                        database.getMatchPlayerJoinDao().insert(matchPlayerJoin);
                    }
                }

                database.close();
                Intent intent = new Intent(match_attribute_selector.this, ScoreCardGrid.class);
                intent.putExtra("matchid", _match.getId());
                intent.putExtra("numberOfHoles", _match.getMaxHole());
                intent.putExtra("numberOfHitsPerHole", _field.getHoleCount());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        database.close();
    }
}