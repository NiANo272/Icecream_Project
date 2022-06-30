package com.example.icecream.ui.notifications;

public class SampleData {
    private String dday;
    private String foodname;
    private String expirydate;

    public SampleData(String dday, String foodname, String expirydate){
        this.dday = dday;
        this.foodname = foodname;
        this.expirydate = expirydate;
    }

    public String getDday()
    {
        return this.dday;
    }

    public String getFoodname()
    {
        return this.foodname;
    }

    public String getExpirydate()
    {
        return this.expirydate;
    }
}

