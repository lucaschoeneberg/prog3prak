package com.example.prog3_ab6.ui;

public class Cell {
    private CellType type;
    private CellStatus status = CellStatus.UNREVEALED;
    private final Integer Size;
    private Integer neighboursMineCount;

    public Cell(Boolean isMine, Integer size) {
        this.type = isMine ? CellType.MINE : CellType.BLANK;
        this.Size = size;
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

    public boolean isUnrevealed() {
        return status == CellStatus.UNREVEALED;
    }

    public void triggered() {

    }
}
