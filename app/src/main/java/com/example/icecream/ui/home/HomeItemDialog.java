package com.example.icecream.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.icecream.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeItemDialog extends DialogFragment {
    public static final String TAG_EVENT_DIALOG = "item_dialog_event";

    public HomeItemDialog(){}

    public static HomeItemDialog getInstance(){
        HomeItemDialog homeItemDialog = new HomeItemDialog();
        return homeItemDialog;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View root = inflater.inflate(R.layout.item_info, container);

        Button btn_confirm = (Button) root.findViewById(R.id.btn_confirm);

        btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        String info_name;
        Bundle bundle =this.getArguments();
        if(bundle != null){
            info_name = bundle.getString("name");
        }
        else
            info_name = "apple";

        TextView tv_info_name = (TextView) root.findViewById(R.id.tv_info_name);
        TextView tv_info_date = (TextView) root.findViewById(R.id.tv_info_date);
        TextView tv_info_quantity = (TextView) root.findViewById(R.id.tv_info_quantity);
        TextView tv_info_category = (TextView) root.findViewById(R.id.tv_info_category);

        /*String rFile = readFile("/data/data/com.example.icecream/files/" + info_name);
        //읽어온 파일 나누기
        String[] txt_split = rFile.split("\\|");
        String name = txt_split[0];
        String category = txt_split[1];
        int year = Integer.parseInt(txt_split[2]);
        int month = Integer.parseInt(txt_split[3]);
        int day = Integer.parseInt(txt_split[4]);*/

        tv_info_name.setText(info_name);
        //tv_info_date.setText(year+"년 "+month+"월 "+day+"일");
        //tv_info_quantity.setText();
        //tv_info_category.setText(category);

        return root;
    }

    public String readFile(String fileName){
        StringBuffer strBuffer = new StringBuffer();
        try{
            InputStream iStream = new FileInputStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iStream));
            String line = "";
            while((line = bufferedReader.readLine()) != null)
                strBuffer.append(line + "\n");
            bufferedReader.close();
            iStream.close();
        }

        catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return strBuffer.toString();
    }
}
