package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.activity.StoryDetailActicity;

import rx.Subscription;


public abstract class BaseFragment extends Fragment {

    protected Subscription subscription;

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unSubscribe();
    }

    protected void unSubscribe(){
        if (subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    protected void getDetail(String data){
        Intent intent = new Intent(getContext(), StoryDetailActicity.class);
        intent.putExtra("id",data);
        startActivity(intent);
    }
}
