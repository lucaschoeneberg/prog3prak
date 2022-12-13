package com.example.prog3_ab06.logic;

import com.example.prog3_ab06.ui.Cell;

public class Game {
    private MineGrid mineGrid;


    public Game(int size) {
        mineGrid = new MineGrid(size);
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }

    public void handleCellClick(Cell cell) {

    }



}
