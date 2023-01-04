package com.example.swingolf.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "matchPlayerJoin", foreignKeys = {
        @ForeignKey(entity = match.class,
                parentColumns = "id",
                childColumns = "matchId",
                onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = player.class,
                parentColumns = "id",
                childColumns = "playerId",
                onDelete = ForeignKey.CASCADE)
})
public class matchPlayerJoin {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "matchId", index = true)
    public long matchId;
    @ColumnInfo(name = "playerId", index = true)
    public long playerId;

    public matchPlayerJoin(long matchId, long playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
    }

    public long getId() {
        return id;
    }

    public long getMatchId() {
        return matchId;
    }

    public long getPlayerId() {
        return playerId;
    }
}
