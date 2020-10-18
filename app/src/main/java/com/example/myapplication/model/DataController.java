package com.example.myapplication.model;

import com.example.myapplication.home.Home_FragmentInterface;

public class DataController {
    public static DataController instance;
    public static DataController getInstance(){
        if(instance==null)
            instance=new DataController();
        return instance;
    }
    Home_FragmentInterface home_fragmentInterface;
    Semister currentsemister;




    //



    public Home_FragmentInterface getHome_fragmentInterface() {
        return home_fragmentInterface;
    }

    public void setHome_fragmentInterface(Home_FragmentInterface home_fragmentInterface) {
        this.home_fragmentInterface = home_fragmentInterface;
    }

    public Semister getCurrentsemister() {
        return currentsemister;
    }

    public void setCurrentsemister(Semister currentsemister) {
        this.currentsemister = currentsemister;
    }
}
