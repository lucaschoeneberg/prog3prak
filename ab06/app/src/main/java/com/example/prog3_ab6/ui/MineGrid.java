package com.example.prog3_ab6.ui;

public class MineGrid {
    private Cell[][] gameGrid;
    private final int amountMines = 10;
    private final int lengthOfField = 10;

    public MineGrid() {
        gameGrid = new Cell[lengthOfField][lengthOfField];

        for (int x = 0; x < lengthOfField; x++) {
            for (int y = 0; y < lengthOfField; y++) {
                gameGrid[x][y] = new Cell();
            }
        }
        minePlacer();
    }

    private void minePlacer() {
       int mineCounter = 0;
        while (mineCounter <= amountMines) {
            int randomX = (int) Math.random() * lengthOfField;
            int randomY = (int) Math.random() * lengthOfField;

            if (!gameGrid[randomX][randomY].isMine()) {
                gameGrid[randomX][randomY].setMine();
                mineCounter++;
            }
        }
    }
}


