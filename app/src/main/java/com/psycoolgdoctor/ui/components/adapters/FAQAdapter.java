package com.psycoolgdoctor.ui.components.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;

import com.psycoolgdoctor.R;
import com.psycoolgdoctor.pojo.FAQData;
import com.psycoolgdoctor.ui.base.RecyclerBaseAdapter;
import com.psycoolgdoctor.utils.Utility;

import java.util.ArrayList;

public class FAQAdapter extends RecyclerBaseAdapter {
    private AppCompatActivity mActivity;
    private View.OnClickListener onClickListener;
    private ArrayList<FAQData> list;
    private int currentPosition = 0;


    public FAQAdapter(AppCompatActivity mActivity, View.OnClickListener onClickListener, ArrayList<FAQData> list) {
        this.mActivity = mActivity;
        this.onClickListener = onClickListener;
        this.list = list;
    }

    @Override
    protected int getLayoutIdForPosition(int position) {
        return R.layout.row_faq;
    }

    @Override
    public Object getViewModel(int position) {
        return list.get(position);
    }

    @Override
    protected void putViewDataBinding(ViewDataBinding viewDataBinding, int position) {
        View view = viewDataBinding.getRoot();
        LinearLayout rootHeader = view.findViewById(R.id.rowFAQItem);
        rootHeader.setTag(position);
        rootHeader.setOnClickListener(onClickListener);

        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvFAQDescription = view.findViewById(R.id.tvFAQDescription);
        tvTitle.setText(list.get(position).getTitle());
        tvFAQDescription.setText(list.get(position).getContent());
        ImageView ivArrow = view.findViewById(R.id.ivArrow);

        LinearLayout linearFAQDescription = view.findViewById(R.id.linearFAQDescription);
        LinearLayout linearFAQTitle = view.findViewById(R.id.linearFAQTitle);
        linearFAQDescription.setVisibility(View.GONE);

        if (currentPosition == position) {
            //toggling visibility
            linearFAQDescription.setVisibility(View.VISIBLE);
            ivArrow.setImageResource(R.drawable.ic_minus);
            tvTitle.setTextColor(mActivity.getResources().getColor(R.color.color_FF9100));

        } else {
            linearFAQDescription.setVisibility(View.GONE);
            ivArrow.setImageResource(R.drawable.ic_plus);
            tvTitle.setTextColor(mActivity.getResources().getColor(R.color.colorBlack));
        }

        linearFAQTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPosition = position; //getting the position of the item to expand it
                notifyDataSetChanged(); //reloding the list
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}

