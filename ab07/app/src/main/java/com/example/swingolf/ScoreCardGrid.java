package com.example.swingolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.swingolf.db.AppDatabase;
import com.example.swingolf.db.entity.player;
import com.example.swingolf.db.entity.match;
import com.example.swingolf.db.entity.field;


import java.util.List;

public class ScoreCardGrid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard_grid);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "swingolf_database2.db").allowMainThreadQueries().build();

        TableLayout table = (TableLayout) findViewById(R.id.grid);

        List<player> players = database.getPlayerDao().getAllPlayers();
        List<match> matches = database.getMatchDao().getAllMatches();


        match _match = null;
        for (match match : matches) if (!match.isFinished) _match = match;
        if (_match != null) {
            field field = database.getFieldDao().getFieldByMatch(_match.getId());

            int ANZAHL_SPIELER = 0;
            for (player player : players) if (player.isPlaying) ANZAHL_SPIELER++;

            for (int i = 0; i < field.holeCount; i++) {
                TableRow row = new TableRow(this);
                TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
                row.setLayoutParams(lp);

                if (i == 0) {
                    TextView title = new TextView(this);
                    title.setText(_match.getName());
                    row.addView(title);

                    for (int j = 0; j < ANZAHL_SPIELER; j++) {
                        TextView player = new TextView(this);
                        player.setText(players.get(j).getName());
                        row.addView(player);
                    }
                }

                if (i > 0) {
                    TextView textView = new TextView(this);
                    textView.setText("Hole " + i + 1);
                    row.addView(textView);

                    for (int j = 0; j < ANZAHL_SPIELER; j++) {
                        EditText editText = new EditText(this);
                        editText.setEnabled(true);
                        editText.setInputType(16);
                        editText.setTransformationMethod(null);
                        editText.addTextChangedListener(new TextWatcher(){
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {}

                            @Override
                            public void afterTextChanged(Editable s) {
                                
                            }
                        });
                        row.addView(editText);
                    }
                }
                table.addView(row, i);
            }
        }
    }
}
