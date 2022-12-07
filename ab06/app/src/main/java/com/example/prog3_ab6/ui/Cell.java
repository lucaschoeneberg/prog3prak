package com.example.prog3_ab6.ui;

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

    public void setMine() {
        this.type = CellType.BLANK;
    }

    public boolean isUnrevealed() {
        return status == CellStatus.UNREVEALED;
    }

    public void triggered() {

    }
}
