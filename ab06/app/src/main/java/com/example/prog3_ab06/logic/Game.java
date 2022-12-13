package com.example.prog3_ab06.logic;

import android.util.Log;

import com.example.prog3_ab06.ui.Cell;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private MineGrid mineGrid;
    private boolean gameOver;
    private boolean flagMode;
    private boolean clearMode;
    private int flagCount;
    private int numberBombs;
    private boolean timeExpired;

    public Game(int size, int numberBombs) {
        this.gameOver = false;
        this.flagMode = false;
        this.clearMode = true;
        this.timeExpired = false;
        this.flagCount = 0;
        this.numberBombs = numberBombs;
        mineGrid = new MineGrid(size);
    }

    public void handleCellClick(Cell cell) {
        if (!gameOver && !isGameWon() && !timeExpired && cell.isUnrevealed()) {
            if (clearMode) {
                clear(cell);
            } else if (flagMode) {
                flag(cell);
            }
        }
    }

    public void clear(Cell cell) {
        getMineGrid().getGameGrid()[cell.getX()][cell.getY()].setRevealed();

        if (cell.isMine()) {
            gameOver = true;
        } else if (cell.isBlanc()) {
            List<Cell> toClear = new ArrayList<>();
            List<Cell> toCheckAdjacents = new ArrayList<>();

            toCheckAdjacents.add(cell);

            while (toCheckAdjacents.size() > 0) {
                Cell c = toCheckAdjacents.get(0);
                int[] cellPos = {c.getX(), c.getY()};
                for (Cell adjacent : getMineGrid().adjacentCells(cellPos[0], cellPos[1])) {
                    if (adjacent.isBlanc()) {
                        if (!toClear.contains(adjacent)) {
                            if (!toCheckAdjacents.contains(adjacent)) {
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent)) {
                            toClear.add(adjacent);
                        }
                    }
                }
                toCheckAdjacents.remove(c);
                toClear.add(c);
            }

            for (Cell c : toClear) {
                c.setRevealed();
            }
        }
    }

    public void flag(Cell cell) {
        cell.setFlagged(!cell.isFlagged());
        int count = 0;
        for (Cell c : getMineGrid().getCells()) {
            if (c.isFlagged()) {
                count++;
            }
        }
        flagCount = count;
    }

    public boolean isGameWon() {
        int numbersUnrevealed = 0;
        for (Cell c : getMineGrid().getCells()) {
            if (c.getValue() != Cell.BOMB && c.getValue() != Cell.BLANK && !c.isRevealed()) {
                numbersUnrevealed++;
            }
        }

        if (numbersUnrevealed == 0) {
            return true;
        } else {
            return false;
        }
    }

    public void toggleMode() {
        clearMode = !clearMode;
        flagMode = !flagMode;
    }

    public void outOfTime() {
        timeExpired = true;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public MineGrid getMineGrid() {
        return mineGrid;
    }

    public boolean isFlagMode() {
        return flagMode;
    }

    public boolean isClearMode() {
        return clearMode;
    }

    public int getFlagCount() {
        return flagCount;
    }

    public int getNumberBombs() {
        return numberBombs;
    }
}