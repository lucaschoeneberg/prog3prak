package com.example.swingolf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
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

        if (_field == null) {
            Intent intent2 = new Intent(this, match_attribute_selector.class);
            startActivity(intent2);
        } else
            for (int i = 0; i < _field.getHoleCount(); i++) {
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
                        editText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                            }

                            @Override
                            public void onTextChanged(CharSequence s, int start, int before, int count) {
                            }

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
