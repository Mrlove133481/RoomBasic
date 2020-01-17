package com.mrlove.roombasic;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;

public class MyViewModel extends AndroidViewModel {
    private SavedStateHandle handle;
    private final static String KEY_DATA = "data";
    public MyViewModel(@NonNull Application application, SavedStateHandle handle) {
        super(application);
        this.handle = handle;
    }

    public MutableLiveData<Integer> getNumber(){
        if(!handle.contains(KEY_DATA)){
            handle.set(KEY_DATA,0);
        }
        return handle.getLiveData(KEY_DATA);
    }

}
