package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import com.example.myapplication.database.CourseDao;
import com.example.myapplication.database.GradeDatabase;
import com.example.myapplication.database.SemisterDao;
import com.example.myapplication.model.Semister;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;


public class GradeRepository {
    private CourseDao courseDao;
    private SemisterDao semisterDao;
    List<Semister>mySemisterList=new ArrayList<>();
    public GradeRepository(Application application){
        GradeDatabase db=GradeDatabase.getdatabase(application);
        courseDao=db.courseDao();
        semisterDao=db.semisterDao();
       // mySemisterList=semisterDao.GetAllSemister();

    }
    public void InsertSemister(Semister semister){
        new InsertTask(semisterDao).execute(semister);
    }
    public List<Semister> GetAllSemister(){
        try {
            mySemisterList=new GetAllSemisterTask(semisterDao).execute().get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return mySemisterList;
    }



    private  static class InsertTask extends AsyncTask<Semister,Void,Void>{
        private SemisterDao dao;
        InsertTask(SemisterDao semisterDao){
            dao=semisterDao;

        }

        @Override
        protected Void doInBackground(Semister... semisters) {
            dao.InsertSemister(semisters[0]);

            return null;
        }
    }
    private static  class GetAllSemisterTask extends AsyncTask<Void,Void, List<Semister>>{
        SemisterDao dao;
        GetAllSemisterTask(SemisterDao semisterDao){
            dao=semisterDao;
        }

        @Override
        protected List<Semister> doInBackground(Void... voids) {
            return dao.GetAllSemister();
        }
    }
}
//create repo9min
