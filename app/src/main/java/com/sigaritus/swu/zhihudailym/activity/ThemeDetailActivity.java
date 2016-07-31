package com.sigaritus.swu.zhihudailym.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ThemeDetailResult;
import com.sigaritus.swu.zhihudailym.fragment.adapter.BaseRecyclerAdapter;
import com.sigaritus.swu.zhihudailym.fragment.adapter.LatestStoryListAdapter;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ThemeDetailActivity extends BaseActivity {
    @Bind(R.id.theme_background)
    ImageView themeBackground;
    @Bind(R.id.theme_detail_list)
    RecyclerView themeDetailList;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    Observer<ThemeDetailResult> observer = new Observer<ThemeDetailResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ThemeDetailResult themeDetailResult) {
            toolbar.setTitle(themeDetailResult.name);
            Glide.with(ThemeDetailActivity.this).load(themeDetailResult.background)
                    .into(themeBackground);
            LatestStoryListAdapter adapter = new LatestStoryListAdapter();

            adapter.setLaststStory(themeDetailResult.storyList);
            adapter.setBackgroundUrl(themeDetailResult.background);
            adapter.setOnRecyclerViewListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
                @Override
                public void onClick(View v, String data) {
                    Intent intent = new Intent(ThemeDetailActivity.this,StoryDetailActicity.class);
                    intent.putExtra("id",data);
                    startActivity(intent);
                }
            });
            themeDetailList.setLayoutManager(new LinearLayoutManager(ThemeDetailActivity.this));
            themeDetailList.setAdapter(adapter);

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theme_detail);
        ButterKnife.bind(this);
        onLoad();
    }

    private void onLoad() {
        String id = getIntent().getStringExtra("id");
        ToastUtils.showLong(id);
        subscription = Network.getZhihuApi()
                .getThemeDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


}
