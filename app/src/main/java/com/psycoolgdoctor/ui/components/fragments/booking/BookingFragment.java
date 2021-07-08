package com.psycoolgdoctor.ui.components.fragments.booking;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.FragmentBookingBinding;
import com.psycoolgdoctor.pojo.HomeData;
import com.psycoolgdoctor.ui.base.BaseFragment;
import com.psycoolgdoctor.ui.components.activities.home.HomeActivity;
import com.psycoolgdoctor.ui.components.adapters.NewBookingAdapter;
import com.psycoolgdoctor.ui.components.adapters.OldBookingAdapter;
import com.psycoolgdoctor.ui.components.fragments.moodOfClient.MoodOfClientFragment;
import com.psycoolgdoctor.utils.Utility;

import java.util.ArrayList;

public class BookingFragment extends BaseFragment {
    private FragmentBookingBinding binding;
    private BookingViewModel homeViewModel;
    private View.OnClickListener onClickListener = null;
    private HomeActivity homeActivity;
    private Bundle bundle;
    private String callBy = "";
    private DrawerLayout drawerLayout;
    private ArrayList<HomeData> list = null;

    @Override
    protected ViewDataBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_booking, container, false);
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
        binding.appBar.tvTitle.setText("Booking");
        RelativeLayout rlHomeMain = mActivity.findViewById(R.id.rlHomeMain);
        rlHomeMain.setVisibility(View.GONE);
        BottomNavigationView bottomNavigationView = mActivity.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(1).setChecked(true);
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
        list = new ArrayList<>();
        list.add(new HomeData(0, "Dinesh Ji", R.drawable.user, ""));
        list.add(new HomeData(0, "Gagan Deep", R.drawable.image_5, ""));
        list.add(new HomeData(0, "Gagan Deep", R.drawable.image_6, ""));
        list.add(new HomeData(0, "Dinesh Ji", R.drawable.user, ""));
        setAdapter();
    }

    private void setAdapter() {
        binding.rvNewBooking.setAdapter(new NewBookingAdapter(mActivity, onClickListener,list));
        binding.rvOldBooking.setAdapter(new OldBookingAdapter(mActivity, onClickListener,list));
    }


    @Override
    protected void initializeOnCreateObject() {
        homeActivity = (HomeActivity) getActivity();
        bundle = getArguments();
        if (bundle != null) {
            callBy = bundle.getString("BookingFragment");
        }
    }


    @Override
    protected void setListeners() {
        binding.appBar.ivMenuBack.setOnClickListener(this);
        binding.tvNewBookings.setOnClickListener(this);
        binding.tvOldBookings.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linearVideo:
                //homeActivity.replaceFragment(new TextChatFragment(), null);
                break;
            case R.id.linearCall:
                Utility.audioCall(mActivity, "7722348969");
                break;
            case R.id.ivMenuBack:
                if (callBy.equals("HomeActivity")) {
                    drawerLayout.openDrawer(GravityCompat.START);
                } else {
                    homeActivity.onBackPressed();
                }
                break;
            case R.id.tvNewBookings:
                setBackground(binding.tvNewBookings, binding.tvOldBookings);
                binding.linearNewBookings.setVisibility(View.VISIBLE);
                binding.linearOldBookings.setVisibility(View.GONE);
                break;
            case R.id.tvOldBookings:
                setBackground(binding.tvOldBookings, binding.tvNewBookings);
                binding.linearOldBookings.setVisibility(View.VISIBLE);
                binding.linearNewBookings.setVisibility(View.GONE);
                break;
            case R.id.rowNewBookingItem:
                //int position = (int) view.getTag();
                //adapter.selectedPos = position;
                homeActivity.changeFragment(new MoodOfClientFragment(),true,null);
                break;
            case R.id.rowOldBookingItem:
                //int position = (int) view.getTag();
                //adapter.selectedPos = position;
                homeActivity.changeFragment(new MoodOfClientFragment(),true,null);
                break;
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setBackground(AppCompatTextView txt1, AppCompatTextView txt2) {
        txt1.setTextColor(mActivity.getResources().getColor(R.color.colorWhite));
        txt1.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.bg_rounded_dark_blue_7sdp));
        txt2.setTextColor(mActivity.getResources().getColor(R.color.colorBlack));
        txt2.setBackgroundDrawable(mActivity.getResources().getDrawable(R.drawable.bg_rounded_white_7sdp));
    }
}