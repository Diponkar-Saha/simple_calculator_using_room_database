package com.example.myapplication;

import android.app.Application;
import android.os.AsyncTask;

import com.example.myapplication.database.CourseDao;
import com.example.myapplication.database.GradeDatabase;
import com.example.myapplication.database.SemisterDao;
import com.example.myapplication.model.Course;
import com.example.myapplication.model.Semister;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.ExecutionException;


public class GradeRepository {
    private CourseDao courseDao;
    private SemisterDao semisterDao;
    List<Semister>mySemisterList=new ArrayList<>();
    List<Course> allcourses=new ArrayList<>();
    public GradeRepository(Application application){
        GradeDatabase db=GradeDatabase.getdatabase(application);
        courseDao=db.courseDao();
        semisterDao=db.semisterDao();

       // mySemisterList=semisterDao.GetAllSemister();

    }
    public void InsertSemister(Semister semister){
        new InsertTask(semisterDao).execute(semister);
    }
    public void InsertCourse(List<Course>myCourse){
        new CourseListTask(courseDao).execute(myCourse);
    }
    public List<Course> GetCourseById(int semesterId){

        try {
            allcourses=new GetCourseListTask(courseDao).execute((semesterId)).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return allcourses;
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
    public void deleteCourse(Course course){
        new deleteCouseTask(courseDao).execute(course);

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
    private static class CourseListTask extends AsyncTask<List<Course>,Void,Void>{
        CourseDao dao;
        CourseListTask(CourseDao courseDao){
            dao=courseDao;

        }
        @Override
        protected Void doInBackground(List<Course>... lists) {
            dao.InsertCourseList(lists[0]);
            return null;
        }
    }
    private  static  class  GetCourseListTask extends AsyncTask<Integer,Void,List<Course>>{
        CourseDao dao;
        GetCourseListTask(CourseDao courseDao){
            dao=courseDao;
        }

        @Override
        protected List<Course> doInBackground(Integer... integers) {

            return dao.GetCourseSemister(integers[0]);
        }
    }
    private static class deleteCouseTask extends AsyncTask<Course,Void,Void>{
        CourseDao dao;
        deleteCouseTask(CourseDao courseDao){
            dao=courseDao;
        }
        @Override
        protected Void doInBackground(Course... courses) {
           dao.DeleteCourse(courses[0]);
            return null;
        }
    }
}
//create repo9min
