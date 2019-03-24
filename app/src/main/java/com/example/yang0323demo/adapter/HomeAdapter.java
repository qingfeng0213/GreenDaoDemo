package com.example.yang0323demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yang0323demo.R;
import com.example.yang0323demo.bean.HomeBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyHodel> {
    Context context;
    List<HomeBean.ResultBean> homelist;
    public HomeAdapter(Context context, List<HomeBean.ResultBean> homelist) {
        this.context = context;
        this.homelist = homelist;
    }

    @NonNull
    @Override
    public MyHodel onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = View.inflate(context, R.layout.activity_item, null);
        MyHodel myHodel = new MyHodel(inflate);
        return myHodel;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHodel myHodel, final int i) {
        myHodel.simpleDraweeView.setImageURI(homelist.get(i).getImageUrl());

        myHodel.textView.setText(homelist.get(i).getName());
        myHodel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(homelist.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return homelist.size();
    }

    public class MyHodel extends RecyclerView.ViewHolder {

        private SimpleDraweeView simpleDraweeView;
        private TextView textView;

        public MyHodel(@NonNull View itemView) {
            super(itemView);
            simpleDraweeView = itemView.findViewById(R.id.ImageView);
            textView = itemView.findViewById(R.id.mytitle);
        }
    }
    public OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onItemClick(String i);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
