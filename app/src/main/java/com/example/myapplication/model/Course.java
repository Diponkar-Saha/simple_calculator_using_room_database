package com.example.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Course {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String course_name;
    public double courseGpa;
    public double courseCredit;
    public int semisterId;

    public Course(double courseGpa, double courseCredit, int semisterId) {
        //this.id = id;
        //this.course_name = course_name;
        this.courseGpa = courseGpa;
        this.courseCredit = courseCredit;
        this.semisterId = semisterId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public double getCourseGpa() {
        return courseGpa;
    }

    public void setCourseGpa(double courseGpa) {
        this.courseGpa = courseGpa;
    }

    public double getCourseCredit() {
        return courseCredit;
    }

    public void setCourseCredit(double courseCredit) {
        this.courseCredit = courseCredit;
    }
    public int getSemisterId() {
        return semisterId;
    }

    public void setSemisterId(int semisterId) {
        this.semisterId = semisterId;
    }

}
