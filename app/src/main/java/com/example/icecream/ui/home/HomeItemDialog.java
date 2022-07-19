package com.example.icecream.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.icecream.R;

public class HomeItemDialog extends DialogFragment {
    public static final String TAG_EVENT_DIALOG = "dialog_event";

    public HomeItemDialog(){}

    public static HomeItemDialog getInstance(){
        HomeItemDialog homeItemDialog = new HomeItemDialog();
        return homeItemDialog;
    }

    @NonNull
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        View root = inflater.inflate(R.layout.item_info, container);

        return root;
    }
}
