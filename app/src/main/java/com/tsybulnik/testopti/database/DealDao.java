package com.tsybulnik.testopti.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.tsybulnik.testopti.model.Deal;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface DealDao {
    @Query("SELECT * FROM deals")
    Flowable<List<Deal>> getAll();


    @Insert
    Completable insert(final Deal deal);

    @Insert
    long addData(Deal deal);
}
