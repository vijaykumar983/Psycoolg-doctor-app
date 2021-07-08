package com.psycoolgdoctor.ui.components.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.pojo.HomeData;
import com.psycoolgdoctor.ui.base.RecyclerBaseAdapter;

import java.util.ArrayList;


public class OldBookingAdapter extends RecyclerBaseAdapter {
    private Context context;
    private View.OnClickListener onClickListener;
    public  int selectedPos = 0;
    private ArrayList<HomeData> list;

    public OldBookingAdapter(Context context, View.OnClickListener onClickListener,ArrayList<HomeData> list) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.list = list;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.row_old_booking;
    }

    @Override
    public Object getViewModel(int position) {
        return list.get(position);
    }

    @Override
    protected void putViewDataBinding(ViewDataBinding viewDataBinding, int position) {
        View view = viewDataBinding.getRoot();
        RelativeLayout rootHeader = view.findViewById(R.id.rowOldBookingItem);
        rootHeader.setTag(position);
        rootHeader.setOnClickListener(onClickListener);
        TextView tvUserName =  view.findViewById(R.id.tvUserName);
        ImageView ivUser =  view.findViewById(R.id.ivUser);

        tvUserName.setText(list.get(position).getName());
        ivUser.setImageResource(list.get(position).getDrawerImage());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

