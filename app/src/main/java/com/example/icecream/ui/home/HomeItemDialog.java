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
import androidx.fragment.app.FragmentResultListener;

import com.example.icecream.R;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class HomeItemDialog extends DialogFragment{
    public static final String TAG_EVENT_DIALOG = "item_dialog_event";
    public HomeItemDialog(){}
    private static String name;
    private TextView tv_info_name;
    private TextView tv_info_date;
    private TextView tv_info_category;
    private TextView tv_info_quantity;

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

        tv_info_name = (TextView) root.findViewById(R.id.tv_info_name);
        tv_info_date = (TextView) root.findViewById(R.id.tv_info_date);
        tv_info_category = (TextView) root.findViewById(R.id.tv_info_category);
        tv_info_quantity = (TextView) root.findViewById(R.id.tv_info_quantity);

        String path = "/data/data/com.example.icecream/files/";
        //파일 이름 저장

        String rFile = readFile("/data/data/com.example.icecream/files/" + name + ".txt");
        //읽어온 파일 나누기
        String[] txt_split = rFile.split("\\|");
        String itemName = txt_split[0];
        String itemCategory = txt_split[1];
        int itemYear = Integer.parseInt(txt_split[2]);
        int itemMonth = Integer.parseInt(txt_split[3]);
        int itemDay = Integer.parseInt(txt_split[4]);
        //int itemQuantity = Integer.parseInt(txt_split[5]); 애는 뭐가 문제인거야...?

        tv_info_name.setText(itemName);
        tv_info_date.setText(itemYear + "년" + itemMonth + "월" + itemDay + "일");
        tv_info_category.setText(itemCategory);
        tv_info_quantity.setText(txt_split[5]);

        return root;
    }

    public void setNameText( String text ) {
       name = text;
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
