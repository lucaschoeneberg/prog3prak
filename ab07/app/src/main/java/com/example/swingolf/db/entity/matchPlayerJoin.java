package com.example.swingolf.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;

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
    public long matchId;
    public long playerId;

    public matchPlayerJoin(long matchId, long playerId) {
        this.matchId = matchId;
        this.playerId = playerId;
    }
}
