package com.example.icecream.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.icecream.R;
import com.example.icecream.databinding.FragmentHomeBinding;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HomeFragment extends Fragment implements View.OnClickListener{

    private FragmentHomeBinding binding;

    private GridView gridView = null;
    private HomeGridViewAdapter adapter = null;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

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

        gridView = (GridView) root.findViewById(R.id.field_home);
        adapter = new HomeGridViewAdapter();


        return root;
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
            Intent intent = new Intent(getActivity(), HomeAddLayout.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(intent);
        }
        else if(v.getId() == R.id.btn_remove){
            Bundle bundle = getArguments();
            String fileName;
            if(bundle != null){
                fileName = bundle.getString("name");
            }
            else{
                fileName = "apple";
            }
            //파일 읽기
            String rFile = readFile("/data/data/com.example.icecream/files/" + fileName + ".txt");
            //읽어온 파일 나누기
            String[] txt_split = rFile.split("\\|");
            String name = txt_split[0];
            String category = txt_split[1];
            int year = Integer.parseInt(txt_split[2]);
            int month = Integer.parseInt(txt_split[3]);
            int day = Integer.parseInt(txt_split[4]);
            //int quantity = Integer.parseInt(txt_split[5]);

            adapter.addItem(new HomeItemInfo(name, category, year, month, day, R.mipmap.ic_launcher_add));
            gridView.setAdapter(adapter);
        }
        else if(v.getId() == R.id.btn_sort){



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