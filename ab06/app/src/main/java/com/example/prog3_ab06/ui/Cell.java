package com.example.prog3_ab06.ui;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private CellType type;
    private CellStatus status = CellStatus.UNREVEALED;
    private Integer neighboursMineCount;
    private Integer size, posX, posY;

    public Cell(int posX, int posY, int size) {
        this.type = CellType.BLANK;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
    }

    public void setInitValues(Cell[][] array) {
        List<Cell> cellsList = new ArrayList<>();
        cellsList.add(cellAt(posX + 1, posY - 1, array));
        cellsList.add(cellAt(posX - 1, posY + 1, array));
        cellsList.add(cellAt(posX, posY + 1, array));
        cellsList.add(cellAt(posX + 1, posY + 1, array));
        cellsList.add(cellAt(posX - 1, posY, array));
        cellsList.add(cellAt(posX + 1, posY, array));
        cellsList.add(cellAt(posX - 1, posY - 1, array));
        cellsList.add(cellAt(posX, posY - 1, array));

        int countBombs = 0;
        for (Cell cell : cellsList) if (cell != null) if (cell.isMine()) countBombs++;
        if (countBombs > 0) this.neighboursMineCount = countBombs;
    }

    public boolean isFlagged() {
        return status == CellStatus.UNREVEALEDFLAG;
    }

    public boolean isMine() {
        return type == CellType.MINE;
    }

    public boolean isBlanc() {
        return type == CellType.BLANK;
    }

    public void setMine() {
        this.type = CellType.BLANK;
    }

    public boolean isUnrevealed() {
        return status == CellStatus.UNREVEALED;
    }

    public int getColor() {
        switch (neighboursMineCount) {
            case 1: {
                return Color.BLUE;
            }
            case 2: {
                return Color.GREEN;
            }
            case 3: {
                return Color.RED;
            }
            default: {
                return Color.WHITE;
            }
        }
    }

    private Cell cellAt(int x, int y, Cell[][] array) {
        if (x < 0 || x >= array.length || y < 0 || y >= array.length) return null;
        return array[x][y * array.length];
    }

    public Integer getNeighboursMineCount() {
        return neighboursMineCount;
    }

    public int getX() {
        return this.posX;
    }

    public int getY() {
        return this.posY;
    }

    public int getSize() {
        return this.size;
    }
}
