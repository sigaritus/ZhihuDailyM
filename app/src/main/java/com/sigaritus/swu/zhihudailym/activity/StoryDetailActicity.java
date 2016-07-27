package com.sigaritus.swu.zhihudailym.activity;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;
import com.sigaritus.swu.zhihudailym.network.Network;


import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class StoryDetailActicity extends AppCompatActivity {


    @Bind(R.id.story_webView)
    WebView story_webView;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    Subscription subscription;

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

            story_webView.loadDataWithBaseURL("file:///android_asset/",html,"text/html","utf-8",null);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stroy_detail);
        ButterKnife.bind(this);
        initViews();
        subscription = Network.getZhihuApi()
                .getDetailStory(getIntent().getStringExtra("id"))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
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
    }
}