package com.example.homeshare.ui.responses;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ResponsesViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ResponsesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is responses fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}