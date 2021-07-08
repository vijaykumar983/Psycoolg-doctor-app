package com.psycoolgdoctor.ui.components.activities.overview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.ActivityOverviewBinding;
import com.psycoolgdoctor.ui.base.BaseBindingActivity;
import com.psycoolgdoctor.ui.components.activities.agreement.AgreementActivity;
import com.psycoolgdoctor.ui.components.adapters.MyEarningAdapter;


public class OverviewActivity extends BaseBindingActivity {
    private ActivityOverviewBinding binding;
    private View.OnClickListener onClickListener = null;

    @Override
    protected void setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_overview);
    }

    @Override
    protected void createActivityObject(Bundle savedInstanceState) {
        mActivity = this;
        onClickListener = this;
    }

    @Override
    protected void initializeObject() {
        binding.appBar.tvTitle.setText("Overview");

        setAdapter();
    }

    private void setAdapter() {
        binding.rvMyEaning.setAdapter(new MyEarningAdapter(mActivity, onClickListener));
    }

    @Override
    protected void setListeners() {
        binding.appBar.ivMenuBack.setOnClickListener(this);
        binding.layoutMilestone.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMenuBack:
                onBackPressed();
                break;
            case R.id.layoutMilestone:
                AgreementActivity.startActivity(mActivity, null, false);
                break;
        }
    }

    public static void startActivity(Activity activity, Bundle bundle, boolean isClear) {
        Intent intent = new Intent(activity, OverviewActivity.class);
        if (bundle != null) intent.putExtra("bundle", bundle);
        if (isClear)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
}
