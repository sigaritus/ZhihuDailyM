package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.activity.StoryDetailActicity;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHotStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuLatestResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuTopStory;
import com.sigaritus.swu.zhihudailym.fragment.adapter.HotStoryListAdapter;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;


public class HotStoryFragment extends BaseFragment {

    @Bind(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.hot_story_list)
    RecyclerView hotStoryList;

    HotStoryListAdapter adapter = new HotStoryListAdapter();



    Observer<ZhihuHotResult> observer = new Observer<ZhihuHotResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            refreshLayout.setRefreshing(false);
            ToastUtils.showShort("error happened :( "+e.getStackTrace()+e.getMessage());

        }

        @Override
        public void onNext(ZhihuHotResult zhihuHotStories) {
            refreshLayout.setRefreshing(false);
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

        adapter.setOnRecyclerViewListener(new HotStoryListAdapter.OnRecyclerViewListener() {
            @Override
            public void onClick(View v, String data) {
                Intent intent = new Intent(getContext(), StoryDetailActicity.class);
                intent.putExtra("id",data);
                startActivity(intent);
            }
        });

        onLoad();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onLoad();
                refreshLayout.setRefreshing(false);
            }
        });

        return view;
    }

    private void onLoad(){
        refreshLayout.setRefreshing(true);
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
