package com.psycoolgdoctor.ui.components.activities.createTicket;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.ActivityCreateTicketBinding;
import com.psycoolgdoctor.pojo.FAQData;
import com.psycoolgdoctor.ui.base.BaseBindingActivity;
import com.psycoolgdoctor.ui.components.adapters.FAQAdapter;
import com.psycoolgdoctor.ui.components.adapters.SupportAdapter;

import java.util.ArrayList;


public class CreateTicketActivity extends BaseBindingActivity {
    private ActivityCreateTicketBinding binding;
    private ArrayList<FAQData> faqData;
    private View.OnClickListener onClickListener = null;

    @Override
    protected void setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_ticket);
    }

    @Override
    protected void createActivityObject(Bundle savedInstanceState) {
        mActivity = this;
        onClickListener = this;
    }

    @Override
    protected void initializeObject() {
        binding.appBar.tvTitle.setText("Create Ticket");
        faqData = new ArrayList<>();
        faqData.add(new FAQData("How can I redeem my earning?","Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        faqData.add(new FAQData("How can I redeem my earning?","Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        faqData.add(new FAQData("How can I redeem my earning?","Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        faqData.add(new FAQData("How can I redeem my earning?","Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        faqData.add(new FAQData("How can I redeem my earning?","Lorem ipsum dolor sit amet, consectetur adipiscing elit."));
        setAdapter();
    }

    private void setAdapter() {
        binding.rvFAQ.setAdapter(new FAQAdapter(mActivity, onClickListener,faqData));
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
        Intent intent = new Intent(activity, CreateTicketActivity.class);
        if (bundle != null) intent.putExtra("bundle", bundle);
        if (isClear)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }
}
