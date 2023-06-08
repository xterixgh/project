package com.example.myapplication;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.net.HttpRetryException;

public class GameViewModel extends ViewModel {
    private MutableLiveData<Boolean> result;
    public  MutableLiveData<Boolean> getResult(){
        if (result == null){
            result = new  MutableLiveData<Boolean>();
        }
        return result;
    }

}
