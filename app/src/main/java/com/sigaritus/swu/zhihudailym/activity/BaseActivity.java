package com.sigaritus.swu.zhihudailym.activity;

import android.support.v7.app.AppCompatActivity;

import rx.Subscription;

public class BaseActivity extends AppCompatActivity {
    protected Subscription subscription;

    @Override
    public void onDestroy() {
        super.onDestroy();
        unSubscribe();
    }

    protected void unSubscribe(){
        if (subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
