package com.example.icecream.ui.notifications;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.icecream.R;

import java.util.ArrayList;

public class NotificationAdapter extends BaseAdapter {

    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    ArrayList<SampleData> sample;

    public NotificationAdapter(Context context, ArrayList<SampleData> data) {
        mContext = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(mContext);

    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Object getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        View v = mLayoutInflater.inflate(R.layout.notificationlist, null);

        TextView dday = v.findViewById(R.id.dday);
        TextView foodname = v.findViewById(R.id.foodname);
        TextView expirydate = v.findViewById(R.id.expirydate);

        dday.setText(sample.get(position).getDday());
        foodname.setText(sample.get(position).getFoodname());
        expirydate.setText("유통기한 : "+sample.get(position).getExpirydate());

        String[] dday_split = dday.getText().toString().split("-");
        if (dday.getText().toString().contains("-")){
            if (Integer.parseInt(dday_split[1])>7){v.setBackgroundColor(Color.BLUE);}
            else if (Integer.parseInt(dday_split[1])>=5){v.setBackgroundColor(Color.GREEN);}
            else {v.setBackgroundColor(Color.YELLOW);}
        }
        else if (dday.getText().toString().contains("+")){
            v.setBackgroundColor(Color.RED);
        }
        else {
            v.setBackgroundColor(Color.YELLOW);
        }

        return v;
    }
}
