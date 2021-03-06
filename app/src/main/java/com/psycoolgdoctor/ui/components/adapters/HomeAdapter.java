package com.psycoolgdoctor.ui.components.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.databinding.ViewDataBinding;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.pojo.HomeData;
import com.psycoolgdoctor.ui.base.RecyclerBaseAdapter;

import java.util.ArrayList;


public class HomeAdapter extends RecyclerBaseAdapter {
    private Context context;
    private View.OnClickListener onClickListener;
    private ArrayList<HomeData> list;
    public  int selectedPos = 0;

    public HomeAdapter(Context context, View.OnClickListener onClickListener, ArrayList<HomeData> list) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.list = list;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.row_home_items;
    }

    @Override
    public Object getViewModel(int position) {
        return list.get(position);
    }

    @Override
    protected void putViewDataBinding(ViewDataBinding viewDataBinding, int position) {
        View view = viewDataBinding.getRoot();
        LinearLayout rootHeader = view.findViewById(R.id.rowHomeItem);
        rootHeader.setTag(position);
        rootHeader.setOnClickListener(onClickListener);
        TextView tvTitle = rootHeader.findViewById(R.id.tvTitle);
        ImageView ivItem = rootHeader.findViewById(R.id.ivItem);
        TextView tvTotalCount = rootHeader.findViewById(R.id.tvTotalCount);
        selectedPos = position;


        tvTitle.setText(list.get(position).getName());
        ivItem.setImageResource(list.get(position).getDrawerImage());
        tvTotalCount.setText(list.get(position).getValue());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

