package com.bw.movie.fragment;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.adapter.ReleaseAdapter;
import com.bw.movie.base.BaseFragment;
import com.bw.movie.base.BasePresenter;
import com.bw.movie.bean.ReleaseBean;
import com.bw.movie.bean.ResultBean;
import com.bw.movie.contract.ReleaseContract;
import com.bw.movie.presenter.RegisterIPresenter;
import com.bw.movie.presenter.ReleaseIPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/*
 *@Auther:cln
 *@Date: 2020/5/7
 *@Time:0:34
 *@Description:
 * */public class ReleaseFragment extends BaseFragment implements ReleaseContract.ReleaseView {
   @BindView(R.id.releaserv)
   XRecyclerView releaserv;
//    @BindView(R.id.releaserv)
//        RecyclerView releaserv;
    ArrayList<ResultBean> list = new ArrayList<>();
    int s=1;
    @Override
    protected BasePresenter initPresenter() {
        return new ReleaseIPresenter(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.release_fragment;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void getData() {

    getget(1,5);
    releaserv.setPullRefreshEnabled(true);
    releaserv.setLoadingMoreEnabled(true);
    releaserv.setLoadingListener(new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            list.clear();
            s=1;
            getget(1,5);
            releaserv.refreshComplete();
        }

        @Override
        public void onLoadMore() {
            s++;
            getget(s,5);
            releaserv.loadMoreComplete();
        }
    });
    }
    public void getget(int page,int count){
        BasePresenter basePresenter = getmPresenter();
//        if (basePresenter!=null&&basePresenter instanceof ReleaseIPresenter){
            ((ReleaseIPresenter) basePresenter).getRelease(page,count);
            Log.i("a", "aaaaaaaaaaa ");
//        }else{
//
//        }
    }
    @Override
    public void onReleaseSuccess(ReleaseBean releaseBean) {
        List<ResultBean> result = releaseBean.getResult();

        if (result!=null){
            list.addAll(result);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        releaserv.setLayoutManager(linearLayoutManager);
        //创建适配器
        ReleaseAdapter releaseAdapter = new ReleaseAdapter(getContext(),list);
        //设置适配器

        releaserv.setAdapter(releaseAdapter);
        releaserv.addItemDecoration(new SpaceItemDecoration(10));
    }

    @Override
    public void onReleaseError(String str) {

    }
    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {
        private int space;

        public SpaceItemDecoration(int space) {
            this.space = space;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            outRect.left = space;
            outRect.right = space;
            outRect.bottom = space;

            // Add top margin only for the first item to avoid double space between items
            if (parent.getChildPosition(view) == 0) {
                outRect.top = space;
            }
        }
    }
}
