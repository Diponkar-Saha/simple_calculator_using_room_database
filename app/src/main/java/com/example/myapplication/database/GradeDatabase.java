package com.example.myapplication.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.Course;
import com.example.myapplication.model.Semister;

@Database(entities = {Course.class, Semister.class},version = 1,exportSchema = false)
public abstract class GradeDatabase extends RoomDatabase {
    public abstract CourseDao courseDao();
    public abstract SemisterDao semisterDao();
    public static volatile GradeDatabase INSTANSE;

    public static GradeDatabase getdatabase(final Context context){
        if(INSTANSE==null){
            INSTANSE= Room.databaseBuilder(context.getApplicationContext(),
                    GradeDatabase.class,"GRADEDATABASE").fallbackToDestructiveMigration()
                    .build();
        }return INSTANSE;
    }
}
