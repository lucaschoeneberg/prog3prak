package com.example.prog3_ab06.ui;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private CellType type;
    private CellStatus status = CellStatus.UNREVEALED;
    private Integer neighboursMineCount;
    private Integer Size;

    public Cell() {
        this.type = CellType.BLANK;
    }

    public void setInitValues(Integer neighboursMineCount) {
        this.neighboursMineCount = neighboursMineCount;
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
            default:{
                return Color.WHITE;
            }
        }
    }

    public List<Cell> getNeighbours(Cell[][] list, int xPos,int yPos){
        List<Cell> adjacentCells = new ArrayList<>();

        List<Cell> cellsList = new ArrayList<>();
        cellsList.add(cellAt(xPos-1, yPos));
        cellsList.add(cellAt(x+1, y));
        cellsList.add(cellAt(x-1, y-1));
        cellsList.add(cellAt(x, y-1));
        cellsList.add(cellAt(x+1, y-1));
        cellsList.add(cellAt(x-1, y+1));
        cellsList.add(cellAt(x, y+1));
        cellsList.add(cellAt(x+1, y+1));

        for (Cell cell: cellsList) {
            if (cell != null) {
                adjacentCells.add(cell);
            }
        }

        return adjacentCells;
    }

    public Integer getNeighboursMineCount() {
        return neighboursMineCount;
    }
}
