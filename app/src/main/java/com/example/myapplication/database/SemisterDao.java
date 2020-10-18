package com.example.myapplication.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.myapplication.model.Semister;

import java.util.List;

@Dao
public interface SemisterDao {
    @Insert
    void InsertSemister(Semister semister);
    @Update
    void DeleteSemister(Semister semister);
    @Update
    void UpadateSemister(Semister semister);
    @Query(("select * from Semister Order by id ASC"))
    List<Semister> GetAllSemister();

}
