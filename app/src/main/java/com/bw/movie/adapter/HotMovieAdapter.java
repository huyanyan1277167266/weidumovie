package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.activity.MovieInfoActivity;
import com.bw.movie.bean.HotMovieBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:cln
 *@Date: 2020/5/11
 *@Time:1:34
 *@Description:
 * */public class HotMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<HotMovieBean.ResultBean> list;

    public HotMovieAdapter(Context context, List<HotMovieBean.ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.release_item, null);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder)holder).iv);
        ((ViewHolder) holder).name.setText(list.get(position).getName());
        ((ViewHolder) holder).dy.setText("导演："+list.get(position).getDirector());
      ((ViewHolder) holder).pf.setText("评分："+list.get(position).getScore());
      ((ViewHolder) holder).zy.setText(list.get(position).getStarring());
      ((ViewHolder) holder).ll.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent = new Intent(context, MovieInfoActivity.class);
              intent.putExtra("movieid",list.get(position).getMovieId()+"");
              context.startActivity(intent);
          }
      });
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
        @BindView(R.id.dy)
        TextView dy;
        @BindView(R.id.zy)
        TextView zy;
        @BindView(R.id.pf)
        TextView pf;
        @BindView(R.id.bt)
        Button bt;
        @BindView(R.id.ll)
        LinearLayout ll;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Unbinder bind = ButterKnife.bind(this, itemView);
        }
    }
}
