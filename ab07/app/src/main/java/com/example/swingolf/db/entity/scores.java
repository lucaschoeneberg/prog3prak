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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "matchId", index = true)
    public long matchId;

    @ColumnInfo(name = "playerId", index = true)
    public long playerId;

    @ColumnInfo(name = "score")
    public int score;

    @ColumnInfo(name = "holeNumber")
    public int holeNumber;

    public scores(long matchId, long playerId, int score, int holeNumber) {
        this.matchId = matchId;
        this.playerId = playerId;
        this.score = score;
        this.holeNumber = holeNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(long playerId) {
        this.playerId = playerId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getHoleNumber() {
        return holeNumber;
    }

    public void setHoleNumber(int holeNumber) {
        this.holeNumber = holeNumber;
    }
}
