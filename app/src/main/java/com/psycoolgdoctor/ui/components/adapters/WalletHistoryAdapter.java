package com.psycoolgdoctor.ui.components.adapters;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.databinding.ViewDataBinding;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.ui.base.RecyclerBaseAdapter;


public class WalletHistoryAdapter extends RecyclerBaseAdapter {
    private Context context;
    private View.OnClickListener onClickListener;

    public WalletHistoryAdapter(Context context, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.row_wallet_history;
    }

    @Override
    public Object getViewModel(int position) {
        return 0;//list.get(position);
    }

    @Override
    protected void putViewDataBinding(ViewDataBinding viewDataBinding, int position) {
        View view = viewDataBinding.getRoot();
        RelativeLayout rootHeader = view.findViewById(R.id.rowWalletHistoryItem);
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

