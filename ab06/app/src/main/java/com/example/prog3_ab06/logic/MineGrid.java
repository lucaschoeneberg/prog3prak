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
                gameGrid[x][y] = new Cell();
            }
        }
        minePlacer();
        mineCounter();
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

    private void mineCounter() {
        for (int x = 0; x < lengthOfField; x++) {
            for (int y = 0; y < lengthOfField; y++) {
                int countBombs = 0;
                List<Cell> cellsList = new ArrayList<>();
                cellsList.add(cellAt(x - 1, y));
                cellsList.add(cellAt(x + 1, y));
                cellsList.add(cellAt(x - 1, y - 1));
                cellsList.add(cellAt(x, y - 1));
                cellsList.add(cellAt(x + 1, y - 1));
                cellsList.add(cellAt(x - 1, y + 1));
                cellsList.add(cellAt(x, y + 1));
                cellsList.add(cellAt(x + 1, y + 1));

                for (Cell cell : cellsList) if (cell != null) if (cell.isMine()) countBombs++;
                if (countBombs > 0) cellAt(x, y).setInitValues(countBombs);
            }
        }
    }

    public Cell cellAt(int x, int y) {
        if (x < 0 || x >= lengthOfField || y < 0 || y >= lengthOfField) return null;
        return gameGrid[x][y * lengthOfField];
    }

}


