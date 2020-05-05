package com.bw.movie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.HotBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:cln
 *@Date: 2020/4/24
 *@Time:21:03
 *@Description:
 * */public class HotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotBean.ResultBean> result;

    public HotAdapter(Context context, List<HotBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.hot_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((ViewHolder)holder).sdv.setImageURI(result.get(0).getHorizontalImage());
        ((ViewHolder) holder).sdvname.setText(result.get(0).getName());
        ((ViewHolder) holder).sdvfs.setText(result.get(0).getScore()+"分");
        Log.i("xxxxx",result.get(0).getName());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        ((ViewHolder) holder).rlv.setLayoutManager(linearLayoutManager);
        //创建适配器
        HotItemAdapter hotItemAdapter = new HotItemAdapter(context, result);
        //设置适配器
        ((ViewHolder) holder).rlv.setAdapter(hotItemAdapter);

    }

    @Override
    public int getItemCount() {
        return 1;
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.sdv)
            SimpleDraweeView sdv;
            @BindView(R.id.sdv_name)
             TextView sdvname;
            @BindView(R.id.sdvfs)
            TextView sdvfs;
            @BindView(R.id.sdvgp)
              Button sdvgp;
            @BindView(R.id.rlv)
            RecyclerView rlv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Unbinder bind = ButterKnife.bind(this, itemView);
        }
    }
}
