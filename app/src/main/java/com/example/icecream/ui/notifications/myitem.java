package com.example.icecream.ui.notifications;

public class myitem {

    String num;
    String name;
    int resId;

    public myitem(String num, String name, int resId){
        this.num = num;
        this.name = name;
        this.resId = resId;
    }

    public String getNum(){
        return num;
    }

    public void setNum(String num){
        this.num = num;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getResId(){
        return resId;
    }

    public void setResId(int resId){
        this.resId = resId;
    }
}
