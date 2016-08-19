package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.activity.StoryDetailActicity;
import com.sigaritus.swu.zhihudailym.bean.ZhihuDetailStory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscription;


public abstract class BaseFragment extends Fragment {

    protected Subscription subscription;
    public static final int REFRESH_DELAY = 2000;

    public static final String KEY_ICON = "icon";
    public static final String KEY_COLOR = "color";

    protected List<Map<String, Integer>> mSampleList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Map<String, Integer> map;
        mSampleList = new ArrayList<>();

        int[] icons = {
                R.drawable.sun,
                R.drawable.sky,
                R.drawable.ic_refresh};

        int[] colors = {
                R.color.saffron,
                R.color.eggplant,
                R.color.sienna};

        for (int i = 0; i < icons.length; i++) {
            map = new HashMap<>();
            map.put(KEY_ICON, icons[i]);
            map.put(KEY_COLOR, colors[i]);
            mSampleList.add(map);
        }
    }

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

    protected void getOfflineDetail(ZhihuDetailStory story){
        Intent intent = new Intent(getContext(), StoryDetailActicity.class);
        Gson gson = new Gson();
        intent.putExtra("detailStory",gson.toJson(story));
        startActivity(intent);
    }
}
