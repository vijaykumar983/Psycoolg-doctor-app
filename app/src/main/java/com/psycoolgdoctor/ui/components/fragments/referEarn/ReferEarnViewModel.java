package com.psycoolgdoctor.ui.components.fragments.referEarn;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReferEarnViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReferEarnViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}