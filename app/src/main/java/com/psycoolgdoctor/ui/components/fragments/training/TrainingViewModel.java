package com.psycoolgdoctor.ui.components.fragments.training;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrainingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TrainingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}