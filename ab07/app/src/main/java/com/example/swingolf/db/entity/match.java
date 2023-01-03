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

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "maxHole")
    public int maxHole;

    public match(long idField, String date, int maxHole) {
        this.idField = idField;
        this.date = date;
        this.maxHole = maxHole;
    }
}