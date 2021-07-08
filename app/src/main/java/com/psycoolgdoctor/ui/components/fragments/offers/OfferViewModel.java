package com.psycoolgdoctor.ui.components.fragments.offers;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OfferViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public OfferViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}