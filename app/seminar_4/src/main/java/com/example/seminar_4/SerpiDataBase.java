package com.example.seminar_4;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Sarpe.class}, version = 1)

public abstract class SerpiDataBase extends RoomDatabase {
    public abstract SerpiDAO getSerpiDAO();

}
