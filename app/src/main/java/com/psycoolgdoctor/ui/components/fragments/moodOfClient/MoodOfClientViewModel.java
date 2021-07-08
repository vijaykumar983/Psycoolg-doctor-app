package com.psycoolgdoctor.ui.components.fragments.moodOfClient;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MoodOfClientViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MoodOfClientViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}