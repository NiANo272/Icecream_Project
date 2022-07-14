package com.example.icecream.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import com.example.icecream.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class HomeAddLayout extends Activity implements View.OnClickListener{

    private GridView gridView = null;
    private HomeGridViewAdapter adapter = null;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);

        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.item_add);

        //버튼 선언
        Button btn_save = (Button) findViewById(R.id.btn_save);
        Button btn_cancel = (Button) findViewById(R.id.btn_cancel);

        btn_save.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        if(v.getId() == R.id.btn_save){
            //이름
            EditText et_Name = (EditText) findViewById(R.id.et_name);
            String name = et_Name.getText().toString();

            //날짜
            EditText et_date = (EditText) findViewById(R.id.et_date);
            String date = et_date.getText().toString();

            //입력된 날짜 " . "을 기준으로 나누기
            String[] date_split = date.split("\\.");

            //String 형식에서 int형으로 바꾸기 (for HomeItemInfo 형식에 맞춤)
            int year = Integer.parseInt(date_split[0]);
            int month = Integer.parseInt(date_split[1]);
            int day = Integer.parseInt(date_split[2]);

            //수량
            EditText et_quantity = (EditText) findViewById(R.id.et_quantity);
            String quantity = et_quantity.getText().toString();

            //카테고리
            EditText et_categroy = (EditText) findViewById(R.id.et_category);
            String category = et_categroy.getText().toString();

            //정보를 txt 형태로 저장
            writeFile(name + ".txt",
                    name + "|" + category + "|" + year + "|" + month + "|" + day + "|" + quantity);


            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle(1);
            bundle.putString("name", name);
            homeFragment.setArguments(bundle);
            TextView textView = (TextView)findViewById(R.id.textView);
            textView.setText("finish");
/*
            Intent intent = new Intent(HomeAddLayout.this, HomeRequestLayout.class);
            intent.putExtra("name", name);
            startActivity(intent);
*/
            this.finish();

        }
        else if(v.getId() == R.id.btn_cancel){
            this.finish();
        }
    }

    private void writeFile(String fileName, String msg){
        try{
            OutputStreamWriter oStreamWriter = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
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

