package com.example.icecream.ui.notifications;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.icecream.databinding.FragmentNotificationsBinding;
import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    private RecyclerView recyclerView;
    private NewAdapter newAdapter;
    ArrayList<SampleData> notification_list;


    int count = 0;
    String path = "/data/data/com.example.icecream/files/";
    File file = new File(path);
    FilenameFilter filter = new FilenameFilter() {
        @Override
        public boolean accept(File file, String s) {
            return s.contains(".txt");
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        notification_list = new ArrayList<SampleData>();



//        final ListView listView  = binding.notificationlist;
//        final NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity(),notificationlist);
//        recyclerView = binding.notificationlist;
//        newAdapter = new NewAdapter(getActivity(),notification_list);
//        this.InitializeFoodData();
//
////        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout., menuItems);
//
////        listView.setAdapter(notificationAdapter);
////        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
////            @Override
////            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
////
////
////            }
////        });
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setAdapter(newAdapter);
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
//        itemTouchHelper.attachToRecyclerView(recyclerView);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);
//        View root = binding.getRoot();
        recyclerView = binding.notificationlist;
        newAdapter = new NewAdapter(getActivity(),notification_list);
        this.InitializeFoodData();

//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout., menuItems);

//        listView.setAdapter(notificationAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//
//
//            }
//        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition();
            SampleData deletedfood = notification_list.get(viewHolder.getAdapterPosition());
            notification_list.remove(viewHolder.getAdapterPosition());
            newAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            File file = new File("/data/data/com.example.icecream/files/"+deletedfood.getFoodname()+".txt");
//            Toast.makeText(getContext(),"swipe success",Toast.LENGTH_LONG);
//            recyclerView.getAdapter().notifyItemRemoved(position);
            Snackbar.make(recyclerView,deletedfood.getFoodname()+" 먹었어요!",Snackbar.LENGTH_LONG).setAction("Undo", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    notification_list.add(position,deletedfood);
                    newAdapter.notifyItemInserted(position);
                }
            }).show();
            file.delete();
        }
    };
    public void InitializeFoodData() {
//
        String[] fileNames = file.list(filter);
        if (fileNames.length > 0) {
            for (int i = count; i < (fileNames.length); i++) {
                String rFile = readFile("/data/data/com.example.icecream/files/" + fileNames[i]);
                //읽어온 파일 나누기
                String[] txt_split = rFile.split("\\|");
                String foodname = txt_split[0];
//                String category = txt_split[1];
                int year = Integer.parseInt(txt_split[2]);
                int month = Integer.parseInt(txt_split[3]);
                int day = Integer.parseInt(txt_split[4]);
                //int quantity = Integer.parseInt(txt_split[5]);
                count++;

                notification_list.add(new SampleData(foodname,year, month, day));
                recyclerView.setAdapter(newAdapter);
            }
            count = 0;
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
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