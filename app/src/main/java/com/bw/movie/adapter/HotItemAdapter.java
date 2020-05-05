package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.HotBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 *@Auther:cln
 *@Date: 2020/4/25
 *@Time:0:23
 *@Description:
 * */public class HotItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotBean.ResultBean> list;

    public HotItemAdapter(Context context, List<HotBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hot_rlv_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).iv.setImageURI(list.get(position).getImageUrl());
        ((ViewHolder) holder).fs.setText(list.get(position).getScore()+"分");
        ((ViewHolder) holder).name.setText(list.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv)
        SimpleDraweeView iv;
        @BindView(R.id.fs)
        TextView fs;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.rlv_gp)
        Button rlvgp;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
