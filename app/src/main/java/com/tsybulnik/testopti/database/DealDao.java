package com.tsybulnik.testopti.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tsybulnik.testopti.model.Deal;

import java.util.List;
@Dao
public interface DealDao {
    @Query("SELECT * FROM deals")
    List<Deal> getAll();

    @Insert
    void insert(Deal deal);
}
