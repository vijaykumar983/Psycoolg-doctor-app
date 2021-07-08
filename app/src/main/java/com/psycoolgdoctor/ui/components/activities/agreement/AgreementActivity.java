package com.psycoolgdoctor.ui.components.activities.agreement;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.ActivityAgreementBinding;
import com.psycoolgdoctor.ui.base.BaseBindingActivity;


public class AgreementActivity extends BaseBindingActivity {
    private ActivityAgreementBinding binding;
    private View.OnClickListener onClickListener = null;

    @Override
    protected void setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_agreement);
    }

    @Override
    protected void createActivityObject(Bundle savedInstanceState) {
        mActivity = this;
        onClickListener = this;
    }

    @Override
    protected void initializeObject() {
        binding.appBar.tvTitle.setText("Agreement");
    }


    @Override
    protected void setListeners() {
        binding.appBar.ivMenuBack.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMenuBack:
                onBackPressed();
                break;
        }
    }

    public static void startActivity(Activity activity, Bundle bundle, boolean isClear) {
        Intent intent = new Intent(activity, AgreementActivity.class);
        if (bundle != null) intent.putExtra("bundle", bundle);
        if (isClear)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
}
