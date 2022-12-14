package com.example.prog3_ab06.logic;

import com.example.prog3_ab06.ui.Cell;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MineGrid implements Iterator<Cell> {
    private final Cell[][] gameGrid;
    private final int amountMines = 10;
    private final int lengthOfField;
    private int rowIndex;
    private int columnIndex;

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
                gameGrid[x][y].setInitValues(this);
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

    public Cell addNeighbourHelper(int x, int y) {
        if (x < 0 || x >= gameGrid.length || y < 0 || y >= gameGrid.length) return null;
        return gameGrid[x][y];
    }

    public Cell[][] getGameGrid() {
        return this.gameGrid;
    }

    @Override
    public boolean hasNext() {
        if (rowIndex >= gameGrid.length)
            return false;
        return columnIndex < gameGrid[rowIndex].length ||
                (rowIndex < gameGrid.length && rowIndex != gameGrid.length - 1);
    }

    @Override
    public Cell next() {
        if (!hasNext())
            throw new NoSuchElementException();
        if (columnIndex >= gameGrid[rowIndex].length) {
            rowIndex++;
            columnIndex = 0;
        }
        return gameGrid[rowIndex][columnIndex++];
    }
}