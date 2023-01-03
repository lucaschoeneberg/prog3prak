package com.example.swingolf.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swingolf.db.entity.match;

import java.util.List;

@Dao
public interface matchDao {
    @Insert
    void insert(match match);

    @Update
    void update(match match);

    @Delete
    void delete(match match);

    @Query("SELECT * FROM `match`")
    List<match> getAllMatches();

    @Query("SELECT * FROM `match` WHERE id=(:id)")
    List<match> getMatchById(int id);
}
