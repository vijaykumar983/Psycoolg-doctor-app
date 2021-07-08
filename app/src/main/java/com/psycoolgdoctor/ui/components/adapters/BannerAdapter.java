package com.psycoolgdoctor.ui.components.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.psycoolgdoctor.R;

public class BannerAdapter extends PagerAdapter {
    private AppCompatActivity mActivity;
    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;
    private int[] list;

    public BannerAdapter(AppCompatActivity mActivity, View.OnClickListener onClickListener, int[] list) {
        this.mActivity = mActivity;
        this.onClickListener = onClickListener;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.row_banner, null);

        ImageView bannerImg = view.findViewById(R.id.imgBanner);
        bannerImg.setImageResource(list[position]);

        ViewPager vp = (ViewPager) container;
        vp.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager vp = (ViewPager) container;
        View view = (View) object;
        vp.removeView(view);
    }
}
