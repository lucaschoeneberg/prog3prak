package com.example.swingolf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ScoreCardGrid extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard_grid);

        TableLayout table = (TableLayout) findViewById(R.id.grid);

        for (int i = 0; i < ANZAHL_LÖCHER; i++) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams lp = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT);
            row.setLayoutParams(lp);

            if (i == 0) {
                TextView title = new TextView(this);
                title.setText(MATCH_NAME);
                row.addView(title);

                for (int j = 0; j < ANZAHL_SPIELER; j++) {
                    TextView player = new TextView(this);
                    player.setText(SPIELER_NAME);
                    row.addView(player);
                }
            }

            if (i < 0) {
                TextView textView = new TextView(this);
                textView.setText("Hole " + i + 1);
                row.addView(textView);

                EditText editText = new EditText(this);
                row.addView(editText);
            }
            table.addView(row, i);
        }
    }
}
