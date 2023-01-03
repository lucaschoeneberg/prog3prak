package com.example.swingolf.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "player")
public class player {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    public player(String name) {
        this.name = name;
    }
}