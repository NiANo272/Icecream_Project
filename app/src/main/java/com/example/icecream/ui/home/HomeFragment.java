package com.example.icecream.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;

import com.example.icecream.FunFunc;
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
import java.util.ArrayList;
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

    //자주쓰는 function
    FunFunc fun = new FunFunc();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        adapter = new HomeGridViewAdapter();
        gridView = (GridView) root.findViewById(R.id.field_home);

        return root;
    }

    //실시간 동기화
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
                String rFile = fun.readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
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
                        String rFile = fun.readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
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
                        String rFile = fun.readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
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

    public class HomeGridViewAdapter extends BaseAdapter {
        String TAG = HomeFragment.class.getSimpleName();
        ArrayList<HomeItemInfo> item = new ArrayList<HomeItemInfo>();


        @Override
        public int getCount() {
            return item.size();
        }

        @Override
        public Object getItem(int i) {
            return item.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public void addItem(HomeItemInfo add_item) {
            item.add(add_item);
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final Context context = viewGroup.getContext();
            final HomeItemInfo myitem = item.get(i);

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.item_icon, viewGroup, false);

                TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
                TextView tv_category = (TextView) view.findViewById(R.id.tv_category);
                ImageView iv_icon = (ImageView) view.findViewById(R.id.iv_icon);

                tv_name.setText(myitem.getName());
                tv_category.setText(myitem.getCategory());
                iv_icon.setImageResource(myitem.getResId());

                Log.d(TAG, "getView() - [ " + i + " ] " + myitem.getName());
            } else {
                View view_1 = new View(context);
                view_1 = (View) view;
            }

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //팝업 생성
                    HomeItemDialog homeItemDialog = HomeItemDialog.getInstance();
                    homeItemDialog.setNameText(myitem.getName());
                    homeItemDialog.show(getChildFragmentManager(), HomeItemDialog.TAG_EVENT_DIALOG);
                }
            });

            //홀리몰리

            return view;

        }
    }

}