package com.example.icecream.ui.notifications;

import java.util.Calendar;

public class SampleData {
    private String dday;
    private String foodname;
    private String expirydate;

    int year,month,day,eyear,emonth,eday;
    int resultday = 0;

    long today,expiryday;

    public SampleData(String foodname, String expirydate){
//        this.dday = dday;
        this.foodname = foodname;
        this.expirydate = expirydate;
    }

    public String getDday()
    {
        int remaindays = calculateDday(this.expirydate);

        if (remaindays>0){
            dday = String.format("D-%d",remaindays);
        }
        else if (remaindays<0){
            dday = String.format("D+%d",Math.abs(remaindays));
        }
        else{
            dday = "D_Day!";
        }
        return dday;
    }

    public String getFoodname()
    {
        return this.foodname;
    }

    public String getExpirydate()
    {
        return this.expirydate;
    }

    public int calculateDday(String expirydate){

        // today date
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        today = calendar.getTimeInMillis();


        // get expirydate
        String[] date_split = expirydate.split("\\.");

        //String 형식에서 int형으로 바꾸기 (for HomeItemInfo 형식에 맞춤)
        eyear = Integer.parseInt(date_split[0]);
        emonth = Integer.parseInt(date_split[1]);
        eday = Integer.parseInt(date_split[2]);

        Calendar eCalendar = Calendar.getInstance();
        eCalendar.set(eyear,emonth-1,eday);
        expiryday = eCalendar.getTimeInMillis();

        long result = (expiryday-today)/(24*60*60*1000);
        resultday = (int)result;

        return resultday;

    }
}

