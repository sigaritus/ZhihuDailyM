package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.activity.StoryDetailActicity;
import com.sigaritus.swu.zhihudailym.bean.ZhihuLatestResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuStory;
import com.sigaritus.swu.zhihudailym.bean.ZhihuTopStory;
import com.sigaritus.swu.zhihudailym.fragment.adapter.BaseRecyclerAdapter;
import com.sigaritus.swu.zhihudailym.fragment.adapter.LatestStoryListAdapter;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class LatestStoryFragment extends BaseFragment implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {


    @Bind(R.id.latest_story_list)
    RecyclerView latestStoryList;
    @Bind(R.id.top_story_header)
    RecyclerViewHeader storyHeader;
    @Bind(R.id.story_slider)
    SliderLayout storySlider;

    Observer<ZhihuLatestResult> observer = new Observer<ZhihuLatestResult>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ZhihuLatestResult zhihuLatestResult) {
            initStoryList(zhihuLatestResult.stories);
            initSlider(zhihuLatestResult.topStories);
        }
    };

    private void initSlider(List<ZhihuTopStory> topStories) {
        for (ZhihuTopStory story:topStories) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(story.getTitle()+"\n")
                    .image(story.getImage())
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(LatestStoryFragment.this);
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("id",story.getId()+"");
            storySlider.addSlider(textSliderView);
        }

        storySlider.setPresetTransformer(SliderLayout.Transformer.DepthPage);
        storySlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        storySlider.setCustomAnimation(new DescriptionAnimation());
        storySlider.setDuration(4000);
        storySlider.addOnPageChangeListener(this);
    }

    private void initStoryList(List<ZhihuStory> stories) {
        latestStoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        LatestStoryListAdapter adapter = new LatestStoryListAdapter();
        adapter.setOnRecyclerViewListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onClick(View v, String data) {
                getDetail(data);
            }
        });
        adapter.setLaststStory(stories);
        latestStoryList.setAdapter(adapter);
        storyHeader.attachTo(latestStoryList);

    }

    public LatestStoryFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_latest_story, container, false);
        ButterKnife.bind(this,view);
        unSubscribe();
        subscription = Network.getZhihuApi()
                .getLatestStory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);

        return view;
    }
    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        storySlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Intent intent = new Intent(getContext(),StoryDetailActicity.class);
        intent.putExtra("id",slider.getBundle().getString("id"));
        startActivity(intent);

    }
}
