package com.example.swingolf.db.dao;

import com.example.swingolf.db.entity.scores;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface scoresDao {
    @Insert
    void insert(scores score);

    @Update
    void update(scores score);

    @Delete
    void delete(scores score);

    @Query("SELECT * FROM scores")
    scores getAllScores();
}
