package com.example.prog3_ab06.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prog3_ab06.helper.OnCellClickListener;
import com.example.prog3_ab06.R;

import java.net.CookieHandler;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MineViewHolder> {
    private List<Cell> cells;
    private OnCellClickListener listener;

    public ViewAdapter(Cell[][] cells, OnCellClickListener listener) {
        this.setCells(cells);
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
        holder.bind(cells.get(position));
        holder.setIsRecyclable(false);
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCells(Cell[][] cells) {
        List<Cell> CellList = new ArrayList<>();
        for (Cell[] cA : cells)
            CellList.addAll(Arrays.asList(cA));
        this.cells = CellList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cells.size();
    }

    class MineViewHolder extends RecyclerView.ViewHolder {
        TextView valueTextView;
        Drawable background;

        public MineViewHolder(@NonNull View itemView) {
            super(itemView);

            valueTextView = itemView.findViewById(R.id.item_cell_value);
            background = itemView.getBackground();
        }

        public void bind(final Cell cell) {
            background.setColorFilter(Color.parseColor("#454246"), PorterDuff.Mode.DST_OVER);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.cellClick(cell);
                }
            });

            if (!cell.isUnrevealed()) {
                if (cell.isMine()) {
                    valueTextView.setText(R.string.bomb);
                } else if (cell.isBlanc() && Objects.equals(cell.getNeighboursMineCount(), "")) {
                    valueTextView.setText("");
                    background.setColorFilter(Color.parseColor("#D4D3CF"), PorterDuff.Mode.DST_OVER);
                } else {
                    valueTextView.setText(String.valueOf(cell.getNeighboursMineCount()));
                    valueTextView.setTextColor(cell.getColor());
                    background.setColorFilter(Color.parseColor("#D4D3CF"), PorterDuff.Mode.DST_OVER);
                }
            } else if (cell.isFlagged()) {
                background.setColorFilter(Color.parseColor("#C4C4C0"), PorterDuff.Mode.DST_OVER);
                valueTextView.setText(R.string.flag);
            }
        }
    }
}
