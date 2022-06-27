package com.example.icecream.ui.myinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyinfoViewModel extends ViewModel{
    private final MutableLiveData<String> mText;

    public MyinfoViewModel(){
        mText = new MutableLiveData<>();
        mText.setValue("This is Myinfo fragment");
    }

    public LiveData<String> getText(){ return mText;}
}
