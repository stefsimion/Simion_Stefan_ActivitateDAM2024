package com.example.seminar_4;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SerpiDAO {

    @Insert
    void insertSarpe(Sarpe sarpe);

    @Query("SELECT * FROM Serpi")
    List<Sarpe> getAllSerpi();

    @Delete
    void deleteSarpe(Sarpe sarpe);

}
