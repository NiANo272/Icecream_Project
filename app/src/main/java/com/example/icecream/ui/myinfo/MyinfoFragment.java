package com.example.icecream.ui.myinfo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.icecream.databinding.FragmentMyinfoBinding;

public class MyinfoFragment extends Fragment{
    
    private FragmentMyinfoBinding binding;
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        MyinfoViewModel myinfoViewModel = 
                new ViewModelProvider(this).get(MyinfoViewModel.class);
        
        binding = FragmentMyinfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        
        final TextView textView = binding.textMyinfo; //textMyinfo 에러나는데 어떻게 해결 해야할 지 모르겠음. >>> layout에서 textview 설치하여 해결
        myinfoViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }
    
    @Override
    public void onDestroyView(){
        super.onDestroyView();
        binding = null;
    }
}
