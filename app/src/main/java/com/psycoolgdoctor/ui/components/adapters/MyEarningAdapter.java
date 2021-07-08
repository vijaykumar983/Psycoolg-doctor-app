package com.psycoolgdoctor.ui.components.adapters;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.databinding.ViewDataBinding;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.ui.base.RecyclerBaseAdapter;


public class MyEarningAdapter extends RecyclerBaseAdapter {
    private Context context;
    private View.OnClickListener onClickListener;

    public MyEarningAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.row_my_earning;
    }

    @Override
    public Object getViewModel(int position) {
        return 0;//list.get(position);
    }

    @Override
    protected void putViewDataBinding(ViewDataBinding viewDataBinding, int position) {
        View view = viewDataBinding.getRoot();
        RelativeLayout rootHeader = view.findViewById(R.id.rowMyEarningItem);
        rootHeader.setTag(position);
        rootHeader.setOnClickListener(onClickListener);
        View view1 = view.findViewById(R.id.view1);

        if (position == 0)
            view1.setVisibility(View.GONE);
        else
            view1.setVisibility(View.VISIBLE);
    }


    @Override
    public int getItemCount() {
        return 3;
    }
}

