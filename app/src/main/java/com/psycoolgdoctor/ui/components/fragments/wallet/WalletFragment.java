package com.psycoolgdoctor.ui.components.fragments.wallet;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.FragmentWalletBinding;
import com.psycoolgdoctor.ui.base.BaseFragment;
import com.psycoolgdoctor.ui.components.activities.home.HomeActivity;
import com.psycoolgdoctor.ui.components.adapters.WalletHistoryAdapter;

import java.util.Calendar;

public class WalletFragment extends BaseFragment {
    private FragmentWalletBinding binding;
    private WalletViewModel homeViewModel;
    private View.OnClickListener onClickListener = null;
    private HomeActivity homeActivity;
    private Bundle bundle;
    private String callBy = "";
    private DrawerLayout drawerLayout;
    private DatePickerDialog picker;

    @Override
    protected ViewDataBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_wallet, container, false);
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
        binding.appBar.tvTitle.setText("Wallet");
        BottomNavigationView bottomNavigationView = mActivity.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
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
        setAdapter();
    }

    private void setAdapter() {
        binding.rvWalletHistory.setAdapter(new WalletHistoryAdapter(mActivity, onClickListener));
    }


    @Override
    protected void initializeOnCreateObject() {
        homeActivity = (HomeActivity) getActivity();
        bundle = getArguments();
        if (bundle != null) {
            callBy = bundle.getString("WalletFragment");
        }
    }


    @Override
    protected void setListeners() {
        binding.appBar.ivMenuBack.setOnClickListener(this);
        binding.tvWalletHistory.setOnClickListener(this);
        binding.tvBonusHistory.setOnClickListener(this);
        binding.linearSelectDate.setOnClickListener(this);
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
            case R.id.tvWalletHistory:
                setBackground(binding.tvWalletHistory, binding.tvBonusHistory);
                // binding.linearNewBookings.setVisibility(View.VISIBLE);
                // binding.linearOldBookings.setVisibility(View.GONE);
                break;
            case R.id.tvBonusHistory:
                setBackground(binding.tvBonusHistory, binding.tvWalletHistory);
                // binding.linearOldBookings.setVisibility(View.VISIBLE);
                // binding.linearNewBookings.setVisibility(View.GONE);
                break;
            case R.id.linearSelectDate:
                showDatePicker(binding.tvDate);
                break;
        }
    }

    private void showDatePicker(AppCompatTextView tvDate) {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(mActivity,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        tvDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setBackground(AppCompatTextView txt1, AppCompatTextView txt2) {
        txt1.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
        txt1.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.bg_rounded_dark_blue_6sdp));
        txt2.setTextColor(mActivity.getResources().getColor(R.color.colorBlack));
        txt2.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.bg_rounded_view_6sdp));
    }
}