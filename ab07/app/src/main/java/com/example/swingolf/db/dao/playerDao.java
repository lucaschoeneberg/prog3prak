package com.example.swingolf.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swingolf.db.entity.player;

@Dao
public interface playerDao {
    @Query("SELECT * FROM player")
    LiveData<player> getAllPlayers();

    @Query("SELECT * FROM player WHERE id=(:id)")
    LiveData<player> getPlayerById(int id);

    @Insert
    void insert(player player);

    @Update
    void update(player player);

    @Delete
    void delete(player player);
}
