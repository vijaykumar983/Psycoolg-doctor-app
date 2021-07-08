package com.psycoolgdoctor.ui.components.fragments.profile;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.FragmentProfileBinding;
import com.psycoolgdoctor.ui.base.BaseFragment;
import com.psycoolgdoctor.ui.components.activities.home.HomeActivity;

import java.util.Calendar;


public class ProfileFragment extends BaseFragment {
    private FragmentProfileBinding binding;
    private ProfileViewModel homeViewModel;
    private View.OnClickListener onClickListener = null;
    private HomeActivity homeActivity;
    private Bundle bundle;
    private String callBy = "";
    private DrawerLayout drawerLayout;
    private TimePickerDialog picker;

    @Override
    protected ViewDataBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        //viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        onClickListener = this;
        binding.setLifecycleOwner(this);
        return binding;
    }

    @Override
    protected void createActivityObject() {
        mActivity = (AppCompatActivity) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        RelativeLayout rlHomeMain = mActivity.findViewById(R.id.rlHomeMain);
        rlHomeMain.setVisibility(View.GONE);
        binding.appBar.tvTitle.setText("Profile");
        BottomNavigationView bottomNavigationView = mActivity.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(3).setChecked(true);
        drawerLayout = mActivity.findViewById(R.id.drawer_layout);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mActivity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusBarAll));
        }
        if (callBy.equals("HomeActivity")) {
            binding.appBar.ivMenuBack.setImageResource(R.drawable.ic_menu);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
        } else {
            binding.appBar.ivMenuBack.setImageResource(R.drawable.ic_left_arrow);
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }

    @Override
    protected void initializeObject() {

    }


    @Override
    protected void initializeOnCreateObject() {
        homeActivity = (HomeActivity) getActivity();
        bundle = getArguments();
        if (bundle != null) {
            callBy = bundle.getString("ProfileFragment");
        }
    }


    @Override
    protected void setListeners() {
        binding.appBar.ivMenuBack.setOnClickListener(this);
        binding.linearStartTime.setOnClickListener(this);
        binding.linearEndTime.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivMenuBack:
                if (callBy.equals("HomeActivity")) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    homeActivity.onBackPressed();
                }
                break;
            case R.id.linearStartTime:
                showTimePicker(binding.tvStartTime);
                break;
            case R.id.linearEndTime:
                showTimePicker(binding.tvEndTime);
                break;
        }
    }

    private void showTimePicker(AppCompatTextView tvTime) {
        final Calendar cldr = Calendar.getInstance();
        int hour = cldr.get(Calendar.HOUR_OF_DAY);
        int minutes = cldr.get(Calendar.MINUTE);
        // time picker dialog
        picker = new TimePickerDialog(mActivity,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        String am_pm = "";
                        if (sHour > 12) {
                            am_pm = "PM";
                            sHour = sHour - 12;
                        } else {
                            am_pm = "AM";
                        }
                        tvTime.setText(sHour +" "+am_pm);
                    }
                }, hour, minutes, true);
        picker.show();


    }

}