package com.example.prog3_ab06.logic;

import com.example.prog3_ab06.ui.Cell;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private MineGrid mineGrid;
    private boolean gameOver;
    private boolean flagMode;
    private boolean clearMode;
    private int flagCount;
    private boolean timeExpired;

    public Game(int size) {
        this.gameOver = false;
        this.flagMode = false;
        this.clearMode = true;
        this.timeExpired = false;
        this.flagCount = 0;
        mineGrid = new MineGrid(size);
    }

    public void handleCellClick(Cell cell) {
        if (!gameOver && !isGameWon() && !timeExpired && cell.isUnrevealed() && !cell.isFlagged()) {
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
        }
        else if (cell.isNumber())
            cell.setRevealed();
        else {
            List<Cell> toClear = new ArrayList<>();
            List<Cell> toCheckAdjacents = new ArrayList<>();

            toCheckAdjacents.add(cell);

            while (toCheckAdjacents.size() > 0) {
                Cell c = toCheckAdjacents.get(0);
                for (Cell adjacent : getMineGrid().getGameGrid()[c.getX()][c.getY()].getCellsList()) {
                    if(c.isNumber())
                        break;
                    if (adjacent.isBlanc() && !toClear.contains(adjacent)) {
                        if (!toCheckAdjacents.contains(adjacent)) {
                            if (!adjacent.isFlagged() && !adjacent.isMine()) {
                                toCheckAdjacents.add(adjacent);
                            }
                        }
                    } else {
                        if (!toClear.contains(adjacent) && !adjacent.isFlagged() && !adjacent.isMine()) {
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
        for (Cell[] cA : getMineGrid().getGameGrid())
            for (Cell c : cA)
                if (c.isFlagged()) count++;
        flagCount = count;
    }

    public boolean isGameWon() {
        int numbersUnrevealed = 0;
        for (Cell[] cA : getMineGrid().getGameGrid())
            for (Cell c : cA)
                if (c.isUnrevealed())
                    numbersUnrevealed++;

        return numbersUnrevealed == 0;
    }

    public void revealBombs() {
        for (Cell[] cA : getMineGrid().getGameGrid())
            for (Cell c : cA)
                if (c.isMine()) c.setRevealed();
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
}