package com.example.icecream.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.icecream.R;
import com.example.icecream.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.Arrays;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FragmentHomeBinding binding;

    private GridView gridView;
    private HomeGridViewAdapter adapter;

    int count = 0;
    //파일 경로
    String path = "/data/data/com.example.icecream/files/";
    //파일 이름 저장
    File file = new File(path);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new HomeGridViewAdapter();
        gridView = (GridView) root.findViewById(R.id.field_home);

//        //상시 아이콘 띄우기
//        //파일 읽기
//        FilenameFilter filter = new FilenameFilter() {
//            @Override
//            public boolean accept(File file, String s) {
//                return s.contains(".txt");
//            }
//
//        };
//        String[] fileNames = file.list(filter);
//        if (fileNames.length > 0) {
//            for (int i = count; i < (fileNames.length); i++) {
//                String rFile = readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
//                //읽어온 파일 나누기
//                String[] txt_split = rFile.split("\\|");
//                String name = txt_split[0];
//                String category = txt_split[1];
//                int year = Integer.parseInt(txt_split[2]);
//                int month = Integer.parseInt(txt_split[3]);
//                int day = Integer.parseInt(txt_split[4]);
//                //int quantity = Integer.parseInt(txt_split[5]);
//                count++;
//
//                adapter.addItem(new HomeItemInfo(name, category, year, month, day, R.mipmap.ic_launcher_add));
//                gridView.setAdapter(adapter);
//            }
//            count = 0;
//        }
//
//        final TextView textView = binding.textHome;
//        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        //버튼 선언
//        Button btn_add = (Button) root.findViewById(R.id.btn_add);
//        Button btn_remove = (Button) root.findViewById(R.id.btn_remove);
//        Button btn_sort = (Button) root.findViewById(R.id.btn_sort);
//        Button btn_category = (Button) root.findViewById(R.id.btn_category);
//        //버튼 onclicklistener 선언
//        btn_add.setOnClickListener(this);
//        btn_remove.setOnClickListener(this);
//        btn_sort.setOnClickListener(this);
//        btn_category.setOnClickListener(this);
//
////        gridView = (GridView) root.findViewById(R.id.field_home);
////        adapter = new HomeGridViewAdapter();

        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = binding.getRoot();
        //상시 아이콘 띄우기
        //파일 읽기
        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File file, String s) {
                return s.contains(".txt");
            }

        };
        String[] fileNames = file.list(filter);
        if (fileNames.length > 0) {
            for (int i = count; i < (fileNames.length); i++) {
                String rFile = readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
                //읽어온 파일 나누기
                String[] txt_split = rFile.split("\\|");
                String name = txt_split[0];
                String category = txt_split[1];
                int year = Integer.parseInt(txt_split[2]);
                int month = Integer.parseInt(txt_split[3]);
                int day = Integer.parseInt(txt_split[4]);
                //int quantity = Integer.parseInt(txt_split[5]);
                count++;

                adapter.addItem(new HomeItemInfo(name, category, year, month, day, R.mipmap.ic_launcher_add));
                gridView.setAdapter(adapter);
            }
        }

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        //버튼 선언
        Button btn_add = (Button) root.findViewById(R.id.btn_add);
        Button btn_remove = (Button) root.findViewById(R.id.btn_remove);
        Button btn_sort = (Button) root.findViewById(R.id.btn_sort);
        Button btn_category = (Button) root.findViewById(R.id.btn_category);
        //버튼 onclicklistener 선언
        btn_add.setOnClickListener(this);
        btn_remove.setOnClickListener(this);
        btn_sort.setOnClickListener(this);
        btn_category.setOnClickListener(this);

//        gridView = (GridView) root.findViewById(R.id.field_home);
//        adapter = new HomeGridViewAdapter();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override //implements View.OnClickListner 추가 필요
    public void onClick(View v){
        //버튼 클릭 이벤트 정의
        if(v.getId() == R.id.btn_add){ //manifests에 <activity android:name=".ui.home.HomeAddLayout" android:theme="@android:style/Theme.DeviceDefault.Light.Dialog"> 추가 필요
            HomeAddDialog homeAddDialog = HomeAddDialog.getInstance();
            homeAddDialog.show(getChildFragmentManager(), HomeAddDialog.TAG_EVENT_DIALOG);

            //파일 읽기
//            FilenameFilter filter = new FilenameFilter() {
//                @Override
//                public boolean accept(File file, String s) {
//                    return s.contains(".txt");
//                }
//
//            };
//            String[] fileNames = file.list(filter);
//            if (fileNames.length > 0) {
//                for (int i = count; i < (fileNames.length); i++) {
//                    String rFile = readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
//                    //읽어온 파일 나누기
//                    String[] txt_split = rFile.split("\\|");
//                    String name = txt_split[0];
//                    String category = txt_split[1];
//                    int year = Integer.parseInt(txt_split[2]);
//                    int month = Integer.parseInt(txt_split[3]);
//                    int day = Integer.parseInt(txt_split[4]);
//                    //int quantity = Integer.parseInt(txt_split[5]);
//                    count++;
//
//                    adapter.addItem(new HomeItemInfo(name, category, year, month, day, R.mipmap.ic_launcher_add));
//                    gridView.setAdapter(adapter);
//                }

//            }
        }
        else if(v.getId() == R.id.btn_remove){

        }
        else if(v.getId() == R.id.btn_sort){

            if (v.isSelected()){
                v.setSelected(false);
                gridView.setAdapter(null);
                count = 0;
                adapter = new HomeGridViewAdapter();
                FilenameFilter filter = new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        return s.contains(".txt");
                    }

                };
                String[] fileNames = file.list(filter);
                if (fileNames.length > 0) {
                    for (int i = count; i < (fileNames.length); i++) {
                        String rFile = readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
                        //읽어온 파일 나누기
                        String[] txt_split = rFile.split("\\|");
                        String name = txt_split[0];
                        String category = txt_split[1];
                        int year = Integer.parseInt(txt_split[2]);
                        int month = Integer.parseInt(txt_split[3]);
                        int day = Integer.parseInt(txt_split[4]);
                        //int quantity = Integer.parseInt(txt_split[5]);
                        count++;

                        adapter.addItem(new HomeItemInfo(name, category, year, month, day, R.mipmap.ic_launcher_add));
                        gridView.setAdapter(adapter);
                    }
                }


            }
            else {
                v.setSelected(true);
                gridView.setAdapter(null);
                count = 0;
                adapter = new HomeGridViewAdapter();
                FilenameFilter filter = new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        return s.contains(".txt");
                    }

                };
                String[] fileNames = file.list(filter);
                Arrays.sort(fileNames);
                if (fileNames.length > 0) {
                    for (int i = count; i < (fileNames.length); i++) {
                        String rFile = readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
                        //읽어온 파일 나누기
                        String[] txt_split = rFile.split("\\|");
                        String name = txt_split[0];
                        String category = txt_split[1];
                        int year = Integer.parseInt(txt_split[2]);
                        int month = Integer.parseInt(txt_split[3]);
                        int day = Integer.parseInt(txt_split[4]);
                        //int quantity = Integer.parseInt(txt_split[5]);
                        count++;

                        adapter.addItem(new HomeItemInfo(name, category, year, month, day, R.mipmap.ic_launcher_add));
                        gridView.setAdapter(adapter);
                    }
                }
            }


        }
        else if(v.getId() == R.id.btn_category){

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