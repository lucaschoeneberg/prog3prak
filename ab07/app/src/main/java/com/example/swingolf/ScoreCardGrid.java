package com.example.swingolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.swingolf.db.AppDatabase;
import com.example.swingolf.db.entity.player;
import com.example.swingolf.db.entity.match;
import com.example.swingolf.db.entity.field;
import com.example.swingolf.db.entity.scores;


import java.util.ArrayList;
import java.util.List;

public class ScoreCardGrid extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard_grid);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "swingolf_database2.db").fallbackToDestructiveMigration().allowMainThreadQueries().build();

        TableLayout table = (TableLayout) findViewById(R.id.grid);

        List<player> players = database.getPlayerDao().getAllPlayers();
        List<match> matches = database.getMatchDao().getAllMatches();
        List<field> fields = database.getFieldDao().getAllFields();

        match _match = matches.get(matches.size() - 1);

        field _field = null;
        for (field f : fields)
            if (f.getId() == _match.getIdField()) _field = f;

        int ANZAHL_SPIELER = 0;
        for (player player : players) if (player.isPlaying) ANZAHL_SPIELER++;
        List<TextView> finish = new ArrayList<>();

        if (_field == null) {
            Intent intent2 = new Intent(this, match_attribute_selector.class);
            startActivity(intent2);
        } else {
            for (int i = 0; i < _field.getHoleCount(); i++) {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                if (i == 0) {
                    TextView title = new TextView(this);
                    title.setText(_match.getName());
                    title.setTextColor(Color.parseColor("#FFFFFF"));
                    row.addView(title);

                    for (int j = 0; j < ANZAHL_SPIELER; j++) {
                        TextView player = new TextView(this);
                        player.setText(players.get(j).getName());
                        player.setTextColor(Color.parseColor("#FFFFFF"));
                        row.addView(player);
                    }
                }

                if (i > 0) {
                    TextView textView = new TextView(this);
                    textView.setText("Hole " + i);
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    row.addView(textView);

                    for (int j = 0; j < ANZAHL_SPIELER; j++) {
                        scores score = new scores(_match.id, players.get(j).getId(), 0, i);
                        database.getScoresDao().insert(score);
                        score = database.getScoresDao().getScrresById(_match.getId(), players.get(j).getId(), i);
                        scores finalScore = score;

                        EditText editText = new EditText(this);
                        editText.setEnabled(true);
                        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
                        editText.setId(j);
                        editText.setTextColor(Color.parseColor("#FFFFFF"));
                        editText.setTransformationMethod(null);
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }

                            @Override
                            public void afterTextChanged(Editable s) {
                                finalScore.setScore(Integer.parseInt(String.valueOf(editText.getText())));
                                database.getScoresDao().update(finalScore);
                                finish.get((int) finalScore.getPlayerId() - 1).setText(editText.getText());
                            }
                        });
                        row.addView(editText);
                    }
                }
                table.addView(row, i);
            }
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);
            row.addView(new TextView(this));
            for (int j = 0; j < ANZAHL_SPIELER; j++) {
                TextView finishPlayer = new TextView(this);
                finishPlayer.setText("0");
                finishPlayer.setTextColor(Color.parseColor("#FFFFFF"));
                row.addView(finishPlayer);
                finish.add(finishPlayer);
            }
            table.addView(row, _field.getHoleCount());
        }
    }
}

