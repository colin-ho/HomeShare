package com.example.homeshare.ui.createInvitation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreateInvitationViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CreateInvitationViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("");
    }

    public LiveData<String> getText() {
        return mText;
    }
}