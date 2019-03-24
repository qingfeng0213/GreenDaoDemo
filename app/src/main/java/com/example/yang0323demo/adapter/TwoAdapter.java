package com.example.yang0323demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.yang0323demo.R;
import com.example.yang0323demo.user.MyUser;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class TwoAdapter extends RecyclerView.Adapter<TwoAdapter.MyHodel>{
    Context context;
    List<MyUser> dataInfos;

    public TwoAdapter(Context context, List<MyUser> dataInfos) {
        this.context = context;
        this.dataInfos = dataInfos;
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
        myHodel.simpleDraweeView.setImageURI(dataInfos.get(i).getImageUrl());

        myHodel.textView.setText(dataInfos.get(i).getName());
        myHodel.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(dataInfos.get(i).getName());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataInfos.size();
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
