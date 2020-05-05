package com.bw.movie.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.ResultBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/*
 *@Auther:cln
 *@Date: 2020/4/24
 *@Time:0:26
 *@Description:
 * */public class RyMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ResultBean> list;

    public RyMovieAdapter(Context context, List<ResultBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.rymovie_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(list.get(position).getImageUrl()).into(((ViewHolder)holder).ryimage);
        ((ViewHolder)holder).ryname.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ryimage)
        ImageView ryimage;
        @BindView(R.id.ryname)
        TextView ryname;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Unbinder bind = ButterKnife.bind(this, itemView);
        }
    }
}
