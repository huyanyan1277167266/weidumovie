package com.bw.movie.fragment;


import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.ComingSoonAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ComingSoonBean;
import com.bw.movie.contract.ComingContract;
import com.bw.movie.presenter.ComingIPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:34
 *@Description:
 * */public class ComingFragment extends BaseFragment implements ComingContract.ComingView {
     @BindView(R.id.comingrlv)
    XRecyclerView comingrlv;
    ArrayList<ComingSoonBean.ResultBean> list = new ArrayList<ComingSoonBean.ResultBean>();
    int s=1;
    @Override
    protected BasePresenter initPresenter() {
        return new ComingIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.coming_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {
        getget(1,5);
        comingrlv.setPullRefreshEnabled(true);
        comingrlv.setLoadingMoreEnabled(true);
        comingrlv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                //先清空集合
                list.clear();
                s=1;
                getget(1,5);
                comingrlv.refreshComplete();

            }

            @Override
            public void onLoadMore() {
                s++;
                getget(s,5);
                comingrlv.loadMoreComplete();
            }
        });
    }



    @Override
    public void onComingSuccess(ComingSoonBean comingSoonBean) {
        List<ComingSoonBean.ResultBean> result = comingSoonBean.getResult();
        if (result!=null){
            list.addAll(result);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        comingrlv.setLayoutManager(linearLayoutManager);
        //创建适配器
        ComingSoonAdapter comingSoonAdapter = new ComingSoonAdapter(getContext(), result);
        comingrlv.setAdapter(comingSoonAdapter);
//        comingrlv.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public void onComingError(String str) {

    }
    public void getget(int page,int count){
        BasePresenter basePresenter = getmPresenter();
        if (basePresenter!=null&&basePresenter instanceof ComingIPresenter){
            ((ComingIPresenter) basePresenter).getComing(1,5);
        }

    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

//        @Override
//        public void getItemOffsets(Rect outRect, View view,
//                                   RecyclerView parent, RecyclerView.State state) {
//            outRect.left = space;
//            outRect.right = space;
//            outRect.bottom = space;
//
//            // Add top margin only for the first item to avoid double space between items
//            if (parent.getChildPosition(view) == 0) {
//                outRect.top = space;
//            }
//        }
    }
}
