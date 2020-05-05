package com.bw.movie.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
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
 *@Date: 2020/4/24
 *@Time:22:01
 *@Description:
 * */public class ComingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ComingBean.ResultBean> cominglist;

    public ComingAdapter(Context context, List<ComingBean.ResultBean> cominglist) {
        this.context = context;
        this.cominglist = cominglist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.coming_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(cominglist.get(position).getImageUrl()).into(((ViewHolder)holder).syiv);
        ((ViewHolder) holder).syname.setText(cominglist.get(position).getName());
        for (int i=0;i<cominglist.size();i++){
            Date date = new Date(cominglist.get(i).getReleaseTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy-MM-dd");
            String format = simpleDateFormat.format(date);
            String[] split = format.split("-");
            ((ViewHolder)holder).sydata.setText(split[1]+"月"+split[2]+"日"+"上映");
        }
       ((ViewHolder) holder).syrs.setText(cominglist.get(position).getWantSeeNum()+"人想看");

    }

    @Override
    public int getItemCount() {
        return cominglist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.syiv)
        ImageView syiv;
        @BindView(R.id.syname)
        TextView syname;
        @BindView(R.id.sydata)
        TextView sydata;
        @BindView(R.id.syrs)
        TextView syrs;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Unbinder bind = ButterKnife.bind(this, itemView);
        }
    }
}
