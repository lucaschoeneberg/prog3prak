package com.example.swingolf.db.entity;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores",
        foreignKeys = {
                @ForeignKey(entity = match.class,
                        parentColumns = "id",
                        childColumns = "matchId",
                        onDelete = CASCADE),
                @ForeignKey(entity = player.class,
                        parentColumns = "id",
                        childColumns = "playerId",
                        onDelete = CASCADE)})
public class scores {
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "matchId")
    public long matchId;

    @ColumnInfo(name = "playerId")
    public long playerId;

    @ColumnInfo(name = "score")
    public int score;

    @ColumnInfo(name = "scorecard")
    public int holeNumber;

    public scores(long matchId, long playerId, int score, int holeNumber) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.score = score;
        this.holeNumber = holeNumber;
    }
}
