package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotResult;
import com.sigaritus.swu.zhihudailym.fragment.adapter.BaseRecyclerAdapter;
import com.sigaritus.swu.zhihudailym.fragment.adapter.HotStoryListAdapter;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;
import com.sigaritus.swu.zhihudailym.widget.phoenix.PullToRefreshView;


import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HotStoryFragment extends BaseFragment {

    @Bind(R.id.pull_to_refresh)
    PullToRefreshView pullToRefreshView;
    @Bind(R.id.hot_story_list)
    RecyclerView hotStoryList;

    HotStoryListAdapter adapter = new HotStoryListAdapter();



    Observer<ZhihuHotResult> observer = new Observer<ZhihuHotResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            pullToRefreshView.setRefreshing(false);
            ToastUtils.showShort("error happened :( "+e.getStackTrace()+e.getMessage());

        }

        @Override
        public void onNext(ZhihuHotResult zhihuHotStories) {
            pullToRefreshView.setRefreshing(false);
            adapter.setHotStories(zhihuHotStories.recent);
        }
    };
    public HotStoryFragment() {
        // Required empty public constructor
    }

    public static HotStoryFragment newInstance() {
        return new HotStoryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_hot_story, container, false);
        ButterKnife.bind(this,view);

        hotStoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        hotStoryList.setAdapter(adapter);

        adapter.setOnRecyclerViewListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onClick(View v, String data) {
                getDetail(data);
            }
        });

        onLoad();

        pullToRefreshView.setOnRefreshListener(new PullToRefreshView.OnRefreshListener() {
            @Override
            public void onRefresh() {
                pullToRefreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        onLoad();
                        pullToRefreshView.setRefreshing(false);
                    }
                }, 200);
            }
        });

        return view;
    }

    private void onLoad(){
        pullToRefreshView.setRefreshing(true);
        unSubscribe();
        subscription = Network.getZhihuApi()
                .getHotStory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
