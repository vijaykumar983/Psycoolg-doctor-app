package com.psycoolgdoctor.ui.components.fragments.home;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.psycoolgdoctor.R;
import com.psycoolgdoctor.databinding.FragmentHomeBinding;
import com.psycoolgdoctor.pojo.HomeData;
import com.psycoolgdoctor.ui.base.BaseFragment;
import com.psycoolgdoctor.ui.components.activities.home.HomeActivity;
import com.psycoolgdoctor.ui.components.activities.overview.OverviewActivity;
import com.psycoolgdoctor.ui.components.adapters.BannerAdapter;
import com.psycoolgdoctor.ui.components.adapters.HomeAdapter;

import java.util.ArrayList;


public class HomeFragment extends BaseFragment {
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;
    private View.OnClickListener onClickListener = null;
    private HomeActivity homeActivity;
    private ArrayList<HomeData> list = null;
    private HomeAdapter adapter;
    private int[] arrayImg = {R.drawable.ic_patient, R.drawable.ic_earning, R.drawable.ic_milestone, R.drawable.ic_clock};
    private int[] bannerImage = {R.drawable.image1, R.drawable.image1};
    private BannerAdapter bannerAdapter;

    @Override
    protected ViewDataBinding setBinding(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
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
    protected void initializeObject() {
        setAdapter();
    }

    private void setAdapter() {
        list = new ArrayList<>();
        String[] mTestArray = getResources().getStringArray(R.array.homeItems);
        String[] value = getResources().getStringArray(R.array.value);

        for (int i = 0; i < mTestArray.length; i++) {
            list.add(new HomeData(i, mTestArray[i], arrayImg[i], value[i]));
        }
        adapter = new HomeAdapter(mActivity, onClickListener, list);
        binding.rvHomeItem.setAdapter(adapter);

        /*bannerAdapter = new BannerAdapter(mActivity, onClickListener, bannerImage);
        binding.viewPager.setAdapter(bannerAdapter);
        binding.dotsIndicator.setViewPager(binding.viewPager);*/

    }

    @Override
    public void onResume() {
        super.onResume();
        RelativeLayout rlHomeMain = mActivity.findViewById(R.id.rlHomeMain);
        rlHomeMain.setVisibility(View.VISIBLE);
        BottomNavigationView bottomNavigationView = mActivity.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.getMenu().getItem(0).setChecked(true);
        DrawerLayout drawerLayout = getActivity().findViewById(R.id.drawer_layout);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = mActivity.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.statusBarDashboard));
        }
    }


    @Override
    protected void initializeOnCreateObject() {
        homeActivity = (HomeActivity) getActivity();
    }


    @Override
    protected void setListeners() {
        binding.linearEarnThisWeek.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rowHomeItem:
                int position = (int) view.getTag();
                adapter.selectedPos = position;

               /* switch (position)
                {
                    case 0:
                        OverviewActivity.startActivity(mActivity,null,false);
                        return;
                    case 1:
                        OverviewActivity.startActivity(mActivity,null,false);
                        return;
                    case 2:
                        OverviewActivity.startActivity(mActivity,null,false);
                        return;
                    case 3:
                        OverviewActivity.startActivity(mActivity,null,false);
                        return;
                }*/
                break;
            case R.id.linearEarnThisWeek:
                OverviewActivity.startActivity(mActivity, null, false);
                break;

        }
    }
}