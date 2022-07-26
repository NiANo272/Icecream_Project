package com.example.icecream.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.icecream.databinding.FragmentNotificationsBinding;

import java.util.ArrayList;

public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;
    ArrayList<SampleData> notificationlist;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NotificationsViewModel notificationsViewModel =
                new ViewModelProvider(this).get(NotificationsViewModel.class);

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        this.InitializeFoodData();

        final ListView listView  = binding.notificationlist;
        final NotificationAdapter notificationAdapter = new NotificationAdapter(getActivity(),notificationlist);
//        notificationsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
//        String[] menuItems = {"number one", "number two", "number three", "number four"};
//        int[] ints = new int[] {dday,.id.foodname,binding.expirydate,};

//        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout., menuItems);

        listView.setAdapter(notificationAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {


            }
        });


        return root;
    }

    public void InitializeFoodData() {
        notificationlist = new ArrayList<SampleData>();

        notificationlist.add(new SampleData("햄버거","2022.7.3"));
        notificationlist.add(new SampleData("피자","2022.6.4"));
        notificationlist.add(new SampleData("치킨","2022.7.15"));
        notificationlist.add(new SampleData("당근","2022.7.1"));
        notificationlist.add(new SampleData("사과","2022.7.7"));
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}