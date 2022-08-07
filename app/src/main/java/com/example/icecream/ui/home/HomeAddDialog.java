package com.example.icecream.ui.home;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.icecream.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class HomeAddDialog extends DialogFragment implements View.OnClickListener{

    public static final String TAG_EVENT_DIALOG = "dialog_event";

    public HomeAddDialog(){}
    public static HomeAddDialog getInstance(){
        HomeAddDialog homeAddDialog = new HomeAddDialog();
        return homeAddDialog;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View root = inflater.inflate(R.layout.item_add, container);

        Button btn_save = (Button) root.findViewById(R.id.btn_save);
        Button btn_cancel = (Button) root.findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이름
                EditText et_Name = (EditText) root.findViewById(R.id.et_name);
                String name = et_Name.getText().toString();

                //날짜
                EditText et_date = (EditText) root.findViewById(R.id.et_date);
                String date = et_date.getText().toString();

                //입력된 날짜 " . "을 기준으로 나누기
                String[] date_split = date.split("\\.");

                //String 형식에서 int형으로 바꾸기 (for HomeItemInfo 형식에 맞춤)
                int year = Integer.parseInt(date_split[0]);
                int month = Integer.parseInt(date_split[1]);
                int day = Integer.parseInt(date_split[2]);

                //수량
                EditText et_quantity = (EditText) root.findViewById(R.id.et_quantity);
                String quantity = et_quantity.getText().toString();

                //카테고리
                EditText et_categroy = (EditText) root.findViewById(R.id.et_category);
                String category = et_categroy.getText().toString();

                //정보를 txt 형태로 저장
                writeFile(name + ".txt",
                        name + "|" + category + "|" + year + "|" + month + "|" + day + "|" + quantity);

                //onResume 으로 돌아가기
                Fragment fragment = getParentFragment();
                fragment.onResume();

                dismiss();

            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return root;
    }
    @Override
    public void onClick(View v){

    }

    private void writeFile(String fileName, String msg){
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(getContext().openFileOutput(fileName, Context.MODE_PRIVATE));
            oStreamWriter.write(msg);
            oStreamWriter.close();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
