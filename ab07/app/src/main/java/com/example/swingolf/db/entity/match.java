package com.example.swingolf.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "match",
        foreignKeys = @ForeignKey(entity = field.class,
                parentColumns = "id",
                childColumns = "filedId",
                onDelete = CASCADE))
public class match {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "filedId")
    public final long idField;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "maxHole")
    public int maxHole;

    @ColumnInfo(name = "isFinished")
    public boolean isFinished;

    public match(String name ,long idField, String date, int maxHole) {
        this.name = name;
        this.idField = idField;
        this.date = date;
        this.maxHole = maxHole;
        this.isFinished = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getIdField() {
        return idField;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getMaxHole() {
        return maxHole;
    }

    public void setMaxHole(int maxHole) {
        this.maxHole = maxHole;
    }
}