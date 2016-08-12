package com.sigaritus.swu.zhihudailym.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.AndroidCharacter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.db.DBManager;
import com.sigaritus.swu.zhihudailym.fragment.adapter.BaseRecyclerAdapter;
import com.sigaritus.swu.zhihudailym.fragment.adapter.LikedStoryListAdapter;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LikedFragment extends BaseFragment {

    @Bind(R.id.liked_story_list)
    RecyclerView likedStoryList;
    LikedStoryListAdapter adapter = new LikedStoryListAdapter();
    public LikedFragment() {
        // Required empty public constructor
    }


    public static LikedFragment newInstance(String param1, String param2) {
        return new LikedFragment();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_liked, container, false);
        ButterKnife.bind(this, view);

        likedStoryList.setLayoutManager(new LinearLayoutManager(getContext()));

        selectStory();

        likedStoryList.setAdapter(adapter);

        return view;
    }

    private void selectStory() {

        Observable.create(new Observable.OnSubscribe<List<ZhihuDetailStory>>() {
            @Override
            public void call(Subscriber<? super List<ZhihuDetailStory>> subscriber) {
                subscriber.onNext(DBManager.getNewInstance(getContext()).queryZhihuDetailStory());
            }
        }).observeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<ZhihuDetailStory>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<ZhihuDetailStory> zhihuDetailStories) {
                        adapter.setmStoryList(zhihuDetailStories);
//                        adapter.setOnRecyclerViewListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
//                            @Override
//                            public void onClick(View v, String data) {
//                                getOfflineDetail(data);
//                            }
//                        });
                    }
                });

    }


}
