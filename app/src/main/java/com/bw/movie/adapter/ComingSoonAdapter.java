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
import com.bw.movie.bean.ComingBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:cln
 *@Date: 2020/5/9
 *@Time:1:29
 *@Description:
 * */public class ComingSoonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ComingBean.ResultBean> list;

    public ComingSoonAdapter(Context context, List<ComingBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.comingsoon_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder)holder).iv);
        ((ViewHolder) holder).name.setText(list.get(position).getName());
        for (int i=0;i<list.size();i++){
            Date date = new Date(list.get(i).getReleaseTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            String format = simpleDateFormat.format(date);
            String[] split = format.split("-");
            ((ViewHolder)holder).data.setText(split[1]+"月"+split[2]+"日"+"上映");
        }
        ((ViewHolder) holder).count.setText(list.get(position).getWantSeeNum()+"人想看");

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.iv)
        ImageView iv;
            @BindView(R.id.name)
        TextView name;
            @BindView(R.id.data)
            TextView data;
            @BindView(R.id.count)
            TextView count;
            @BindView(R.id.bt)
        Button bt;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Unbinder bind = ButterKnife.bind(this, itemView);
        }
    }
}
