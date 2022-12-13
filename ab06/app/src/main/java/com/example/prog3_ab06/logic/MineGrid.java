package com.example.prog3_ab06.logic;

import com.example.prog3_ab06.ui.Cell;

import java.util.ArrayList;
import java.util.List;

public class MineGrid {
    private Cell[][] gameGrid;
    private final int amountMines = 10;
    private final int lengthOfField;

    public MineGrid(int size) {
        lengthOfField = size;
        gameGrid = new Cell[lengthOfField][lengthOfField];

        for (int x = 0; x < lengthOfField; x++) {
            for (int y = 0; y < lengthOfField; y++) {
                gameGrid[x][y] = new Cell(x, y, lengthOfField / 11);
            }
        }
        minePlacer();

        for (int x = 0; x < lengthOfField; x++)
            for (int y = 0; y < lengthOfField; y++)
                gameGrid[x][y].setInitValues(gameGrid);
    }

    private void minePlacer() {
        int mineCounter = 0;
        while (mineCounter <= amountMines) {
            int randomX = (int) (Math.random() * lengthOfField);
            int randomY = (int) (Math.random() * lengthOfField);

            if (!gameGrid[randomX][randomY].isMine()) {
                gameGrid[randomX][randomY].setMine();
                mineCounter++;
            }
        }
    }

    private Cell cellAt(int x, int y) {
        if (x < 0 || x >= gameGrid.length || y < 0 || y >= gameGrid.length) return null;
        return gameGrid[x][y];
    }

    public Cell[][] getGameGrid() {
        return this.gameGrid;
    }
}