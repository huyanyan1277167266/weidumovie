package com.bw.movie.fragment;

import android.view.View;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.HotMovieAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.HotMovieBean;
import com.bw.movie.contract.HotContract;
import com.bw.movie.presenter.HotIPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:34
 *@Description:
 * */public class HotFragment extends BaseFragment implements HotContract.HotView {
     @BindView(R.id.xlv)
    XRecyclerView xlv;
    ArrayList<HotMovieBean.ResultBean> list = new ArrayList<>();
    int b=1;
    @Override
    protected BasePresenter initPresenter() {
        return new HotIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.hot_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {
        getget(1,5);
        xlv.setPullRefreshEnabled(true);
        xlv.setLoadingMoreEnabled(true);
        xlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
               list.clear();
               b=1;
               getget(1,5);
               xlv.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                b++;
                getget(b,5);
                xlv.loadMoreComplete();
            }
        });
    }

    @Override
    public void HotSuccess(HotMovieBean hotMovieBean) {
        List<HotMovieBean.ResultBean> result = hotMovieBean.getResult();
        if (result!=null){
            list.addAll(result);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        xlv.setLayoutManager(linearLayoutManager);
        //创建适配器
        HotMovieAdapter hotMovieAdapter = new HotMovieAdapter(getContext(), result);
        xlv.setAdapter(hotMovieAdapter);
    }

    @Override
    public void HotError(String str) {

    }
    public void getget(int page,int count){
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter!=null&&basePresenter instanceof HotIPresenter){
            ((HotIPresenter) basePresenter).getHot(page,count);
        }
    }
}
