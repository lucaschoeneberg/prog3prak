package com.example.swingolf.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.swingolf.db.entity.match;
import com.example.swingolf.db.entity.matchPlayerJoin;
import com.example.swingolf.db.entity.player;

import java.util.List;

@Dao
public interface matchPlayerJoinDao {
    @Insert
    void insert(matchPlayerJoin matchPlayerJoin);

    @Query("SELECT * FROM player INNER JOIN matchPlayerJoin ON " +
            "player.id = matchPlayerJoin.playerId WHERE " +
            "matchPlayerJoin.matchId = :matchId")
    List<player> getPlayersForMatch(final int matchId);

    @Query("SELECT * FROM 'match' INNER JOIN matchPlayerJoin ON " +
            "'match.id' = matchPlayerJoin.matchId WHERE " +
            "matchPlayerJoin.playerId = :playerId")
    List<match> getMatchesForPlayer(final int playerId);
}
