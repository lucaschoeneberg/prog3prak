package com.example.swingolf.db.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.swingolf.db.entity.field;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Dao
public interface fieldDao {
    @Insert
    void insert(field field);

    @Update
    void update(field field);

    @Delete
    void delete(field field);

    @Query("SELECT * FROM field")
    ArrayList<field> getAllFields();

}
