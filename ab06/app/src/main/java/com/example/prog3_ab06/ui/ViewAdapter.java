package com.example.prog3_ab06.ui;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.prog3_ab06.helper.OnCellClickListener;
import com.example.prog3_ab06.R;

public class
ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MineViewHolder> {
    private Cell[] cells;
    private OnCellClickListener listener;

    public ViewAdapter(Cell[] cells, OnCellClickListener listener) {
        this.cells = cells;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewAdapter.MineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cell, parent, false);
        return new MineViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MineViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MineViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;

        public MineViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.item_cell_value);
        }

        public void bind(final Cell cell) {
            itemView.setBackgroundColor(Color.GRAY);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.cellClick(cell);
                }
            });

            if (!cell.isUnrevealed()) {
                if (cell.isMine()) {
                    valueTextView.setText(R.string.bomb);
                } else if (cell.isBlanc()) {
                    valueTextView.setText("");
                    itemView.setBackgroundColor(Color.WHITE);
                } else {
                    valueTextView.setText(String.valueOf(cell.getNeighboursMineCount()));
                    valueTextView.setTextColor(cell.getColor());
                }
            } else if (cell.isFlagged()) {
                valueTextView.setText(R.string.flag);
            }
        }
    }
}
