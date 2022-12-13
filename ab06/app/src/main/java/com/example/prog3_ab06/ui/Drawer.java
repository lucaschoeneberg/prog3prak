package com.example.prog3_ab06.ui;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Timer;

public class Drawer {
    private Paint painter;
    private Canvas canvas;

    public Drawer(Paint painter, Canvas canvas){
        this.painter = painter;
        this.canvas = canvas;
    }

    public void draw(Cell cell){
        if (cell.isUnrevealed()){
            painter.setColor(Color.GRAY);
            canvas.drawRect(cell.getX(), cell.getY(), cell.getX() + cell.getSize(), cell.getY() + cell.getSize(), painter);
        } else {
            painter.setColor(cell.getColor());
            canvas.drawRect(cell.getX(), cell.getY(), cell.getX() + cell.getSize(), cell.getY() + cell.getSize(), painter);
            painter.setColor(Color.BLACK);
            canvas.drawText(cell.getNeighboursMineCount().toString(), cell.getX() + cell.getSize() / 2, cell.getY() + cell.getSize() / 2, painter);
        }
    }

    public void draw(Timer timer){
        painter.setColor(Color.BLACK);
        canvas.drawText(timer.toString(), 0, 0, painter);
    }

    public void draw(Integer score){
        painter.setColor(Color.BLACK);
        canvas.drawText(score.toString(), 0, 0, painter);
    }

}
