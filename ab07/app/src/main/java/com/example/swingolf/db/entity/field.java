package com.example.swingolf.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "field")
public class field {
    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "holeCount")
    public int holeCount;

    public field(String name, int holeCount) {
        this.name = name;
        this.holeCount = holeCount;
    }
}