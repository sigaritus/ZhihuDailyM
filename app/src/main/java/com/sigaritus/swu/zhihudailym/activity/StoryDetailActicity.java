package com.sigaritus.swu.zhihudailym.activity;

import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.google.gson.Gson;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.db.DBManager;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;


import java.lang.reflect.Type;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StoryDetailActicity extends BaseActivity {

    @Bind(R.id.story_webView)
    WebView story_webView;
    @Bind(R.id.story_fab)
    FabSpeedDial fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    ZhihuDetailStory saveTemp;
    ZhihuDetailStory storyTemp;
    String css = "<link rel=\"stylesheet\" href=\"zhihu_master.css\" type=\"text/css\">";

    Observer<ZhihuDetailStory> observer = new Observer<ZhihuDetailStory>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ZhihuDetailStory zhihuDetailStory) {
            saveTemp = zhihuDetailStory;
            renderPage(zhihuDetailStory);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroy_detail);
        ButterKnife.bind(this);
        initViews();

        if (getIntent().getAction().equals("id")) {
            subscription = Network.getZhihuApi()
                    .getDetailStory(getIntent().getStringExtra("id"))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(observer);
        } else if (getIntent().getAction().equals("detailStory")) {
            Gson gson = new Gson();
            storyTemp = gson.fromJson(getIntent().getStringExtra("detailStory"), ZhihuDetailStory.class);
            renderPage(storyTemp);
        }
    }

    private void initViews() {
        story_webView.setScrollbarFadingEnabled(true);
        //能够和js交互
        story_webView.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        story_webView.getSettings().setBuiltInZoomControls(false);
        //缓存
        story_webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        story_webView.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        story_webView.getSettings().setAppCacheEnabled(false);

        story_webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        fab.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int id = menuItem.getItemId();

                if (id == R.id.story_like) {

                    if (null != saveTemp) {
                        saveStory(saveTemp);
                        ToastUtils.showShort("收藏成功");
                    }

                    return true;
                } else if (id == R.id.story_share) {

                    return true;
                }
                return false;
            }
        });

    }

    private void saveStory(final ZhihuDetailStory saveTemp) {

        Observable.create(new Observable.OnSubscribe<ZhihuDetailStory>() {
            @Override
            public void call(Subscriber<? super ZhihuDetailStory> subscriber) {
                DBManager.getNewInstance(StoryDetailActicity.this).insertStory(saveTemp);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) // 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDetailStory>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        ToastUtils.showLong(e.getMessage());
                    }

                    @Override
                    public void onNext(ZhihuDetailStory zhihuDetailStory) {
                        ToastUtils.showLong("保存成功");
                    }
                });

    }

    private void renderPage(ZhihuDetailStory zhihuDetailStory) {
        toolbar.setTitle(zhihuDetailStory.getTitle());
        String content = zhihuDetailStory.getBody().replace("<div class=\"img-place-holder\">", "");
        String html = "<!DOCTYPE html>\n"
                + "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\">\n"
                + "<head>\n"
                + "\t<meta charset=\"utf-8\" />"
                + css
                + "\n</head>\n"
                + content
                + "</body></html>";

        story_webView.loadDataWithBaseURL("file:///android_asset/", html, "text/html", "utf-8", null);

    }


}