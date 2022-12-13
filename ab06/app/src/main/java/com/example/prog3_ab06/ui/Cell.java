package com.example.prog3_ab06.ui;

import android.graphics.Color;

import com.example.prog3_ab06.logic.MineGrid;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private CellType type;
    private CellStatus status = CellStatus.UNREVEALED;
    private Integer neighboursMineCount;
    private final Integer size;
    private final Integer posX;
    private final Integer posY;
    private final List<Cell> cellsList;

    public Cell(int posX, int posY, int size) {
        this.type = CellType.BLANK;
        this.posX = posX;
        this.posY = posY;
        this.size = size;
        cellsList = new ArrayList<>();
    }

    public void setInitValues(MineGrid grid) {
        cellsList.clear();

        addToList(grid.cellAt(posX + 1, posY - 1));
        addToList(grid.cellAt(posX - 1, posY + 1));
        addToList(grid.cellAt(posX, posY + 1));
        addToList(grid.cellAt(posX + 1, posY + 1));
        addToList(grid.cellAt(posX - 1, posY));
        addToList(grid.cellAt(posX + 1, posY));
        addToList(grid.cellAt(posX - 1, posY - 1));
        addToList(grid.cellAt(posX, posY - 1));

        int countBombs = 0;
        for (Cell cell : cellsList) if (cell != null) if (cell.isMine()) countBombs++;
        if (countBombs > 0) this.neighboursMineCount = countBombs;
    }

    void addToList(Cell c) {
        if (c != null) cellsList.add(c);
    }

    public boolean isFlagged() {
        return status == CellStatus.UNREVEALEDFLAG;
    }

    public void setFlagged(Boolean state) {
        if (state) status = CellStatus.UNREVEALEDFLAG;
        else status = CellStatus.UNREVEALED;
    }

    public boolean isMine() {
        return type == CellType.MINE;
    }

    public boolean isBlanc() {
        return type == CellType.BLANK;
    }

    public void setMine() {
        this.type = CellType.MINE;
    }

    public boolean isUnrevealed() {
        return status == CellStatus.UNREVEALED || status == CellStatus.UNREVEALEDFLAG;
    }

    public void setRevealed() {
        this.status = CellStatus.REVEALED;
    }

    public int getColor() {
        switch (neighboursMineCount) {
            case 1: {
                return Color.parseColor("#7291A4");
            }
            case 2: {
                return Color.parseColor("#3A8556");
            }
            case 3: {
                return Color.parseColor("#D86E3A");
            }
            default: {
                return Color.WHITE;
            }
        }
    }

    public String getNeighboursMineCount() {
        if (neighboursMineCount != null)
            return neighboursMineCount.toString();
        else return "";
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

    public List<Cell> getCellsList() {
        return cellsList;
    }
}