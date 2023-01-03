package com.example.swingolf.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swingolf.db.entity.player;

import java.util.List;

@Dao
public interface playerDao {
    @Query("SELECT * FROM player")
    List<player> getAllPlayers();

    @Query("SELECT * FROM player WHERE id = :id")
    List<player> getPlayerById(int id);

    @Query("DELETE FROM player WHERE player.id = :userId")
    void deletePlayerById(long userId);

    @Query("DELETE FROM player WHERE player.name = :userName")
    void deletePlayerByName(String userName);

    @Query("DELETE FROM player")
    void deleteAllPlayers();

    @Insert
    void insert(player player);

    @Update
    void update(player player);

    @Delete
    void delete(player player);
}
