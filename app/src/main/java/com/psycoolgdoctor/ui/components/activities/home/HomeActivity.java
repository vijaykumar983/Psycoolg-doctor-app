package com.psycoolgdoctor.ui.components.activities.home;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.ActivityHomeBinding;
import com.psycoolgdoctor.databinding.DialogLogoutBinding;
import com.psycoolgdoctor.pojo.DrawerData;
import com.psycoolgdoctor.ui.base.BaseBindingActivity;
import com.psycoolgdoctor.ui.components.activities.login.LoginActivity;
import com.psycoolgdoctor.ui.components.adapters.DrawerAdapter;
import com.psycoolgdoctor.ui.components.fragments.blog.BlogFragment;
import com.psycoolgdoctor.ui.components.fragments.booking.BookingFragment;
import com.psycoolgdoctor.ui.components.fragments.home.HomeFragment;
import com.psycoolgdoctor.ui.components.fragments.offers.OfferFragment;
import com.psycoolgdoctor.ui.components.fragments.profile.ProfileFragment;
import com.psycoolgdoctor.ui.components.fragments.quotes.QuotesFragment;
import com.psycoolgdoctor.ui.components.fragments.ranking.RankingFragment;
import com.psycoolgdoctor.ui.components.fragments.referEarn.ReferEarnFragment;
import com.psycoolgdoctor.ui.components.fragments.support.SupportFragment;
import com.psycoolgdoctor.ui.components.fragments.training.TrainingFragment;
import com.psycoolgdoctor.ui.components.fragments.wallet.WalletFragment;
import com.psycoolgdoctor.utils.Utility;

import java.util.ArrayList;

public class HomeActivity extends BaseBindingActivity {
    private ActivityHomeBinding binding;
    private ActionBarDrawerToggle toggle;
    private ArrayList<DrawerData> list = null;
    private DrawerAdapter adapter = null;
    private View.OnClickListener onClickListener = null;
    private Bundle bundle;


    @Override
    protected void setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        onClickListener = this;
    }

    @Override
    protected void createActivityObject(Bundle savedInstanceState) {
        mActivity = this;
    }


    @Override
    protected void initializeObject() {
        setActionBar();
        bundle = new Bundle();
        replaceFragment(new HomeFragment(), null);

        setDrawerAdapter();
    }

    private void setDrawerAdapter() {
        list = new ArrayList<>();
        String[] mTestArray = getResources().getStringArray(R.array.drawerNames);

        for (int i = 0; i < mTestArray.length; i++) {
            list.add(new DrawerData(i, mTestArray[i]));
        }

        adapter = new DrawerAdapter(mActivity, list, onClickListener);
        binding.recyclerView.setAdapter(adapter);
    }

    private void setActionBar() {
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.layoutMain.appBar.toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                Utility.hideKeyboard(mActivity);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                Utility.hideKeyboard(mActivity);
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        toggle.setDrawerIndicatorEnabled(false);


        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toggle.setHomeAsUpIndicator(R.drawable.ic_menu);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                } else binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    @Override
    protected void setListeners() {
        binding.navHeader.imvClose.setOnClickListener(this);
        binding.navHeader.tvEditProfile.setOnClickListener(this);

        binding.layoutMain.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menuHome:
                        replaceFragment(new HomeFragment(), null);
                        return true;
                    case R.id.menuBooking:
                        bundle.putString("BookingFragment", "HomeActivity");
                        replaceFragment(new BookingFragment(), bundle);
                        return true;
                    case R.id.menuWallet:
                        bundle.putString("WalletFragment", "HomeActivity");
                        replaceFragment(new WalletFragment(), bundle);
                        return true;
                    case R.id.menuProfile:
                        bundle.putString("ProfileFragment", "HomeActivity");
                        replaceFragment(new ProfileFragment(), bundle);
                        return true;
                }
                return false;
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main: {
                int position = (int) view.getTag();
                adapter.selectedPos = position;
                //adapter.notifyDataSetChanged();


                switch (position) {
                    case 0:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new ProfileFragment(), null);
                        return;
                    case 1:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new BookingFragment(), null);
                        return;
                    case 2:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                         replaceFragment(new WalletFragment(), null);
                        return;
                    case 3:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new BlogFragment(), null);
                        return;
                    case 4:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new QuotesFragment(), null);
                        return;
                    case 5:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new OfferFragment(), null);
                        return;
                    case 6:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new RankingFragment(), null);
                        return;
                    case 7:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new SupportFragment(), null);
                        return;
                    case 8:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new TrainingFragment(), null);
                        return;
                    case 9:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        replaceFragment(new ReferEarnFragment(), null);
                        return;
                    case 10:
                        binding.drawerLayout.closeDrawer(GravityCompat.START);
                        logOutDialog();
                        return;
                }
                binding.drawerLayout.closeDrawer(GravityCompat.START);
            }
            break;
            case R.id.imvClose:
                binding.drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.tvEditProfile:
               /* binding.drawerLayout.closeDrawer(GravityCompat.START);
                replaceFragment(new ProfileFragment(), null);
                break;*/
        }
    }

    private void logOutDialog() {
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog);
        DialogLogoutBinding dialogBinding = DataBindingUtil.inflate(LayoutInflater.from(mActivity), R.layout.dialog_logout, null, false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(dialogBinding.getRoot());

        dialogBinding.tvTitle.setText("Logout");
        dialogBinding.tvMessage.setText("Are you sure, you want to logout?");
        dialogBinding.btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialogBinding.btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                sessionManager.logout();
                LoginActivity.startActivity(mActivity, null, true);
                finish();
            }
        });

        dialog.show();
    }


    public static void startActivity(Activity activity, Bundle bundle, boolean isClear) {
        Intent intent = new Intent(activity, HomeActivity.class);
        if (bundle != null) intent.putExtra("bundle", bundle);
        if (isClear)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        activity.startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            int fragments = getSupportFragmentManager().getBackStackEntryCount();
            if (fragments == 1) {
                finish();
            } else if (fragments == 2) {
                //replaceFragment(new HomeFragment(), null);
            }
            super.onBackPressed();
        }
    }
}
