package com.psycoolgdoctor.ui.components.activities;

import android.os.Bundle;
import android.os.Handler;

import androidx.databinding.DataBindingUtil;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.ActivitySplashBinding;
import com.psycoolgdoctor.ui.base.BaseBindingActivity;
import com.psycoolgdoctor.ui.components.activities.intro.IntroActivity;


public class SplashActivity extends BaseBindingActivity {
    private ActivitySplashBinding binding;

    @Override
    protected void setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash);
    }

    @Override
    protected void createActivityObject(Bundle savedInstanceState) {
        mActivity = this;
    }

    @Override
    protected void initializeObject() {
        startSplashTimer();
    }

    @Override
    protected void setListeners() {

    }

    private void startSplashTimer() {
        try {

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    goNextScreen();
                }
            }, 3000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void goNextScreen() {
        IntroActivity.startActivity(this, null, true);
        finish();
    }
}
