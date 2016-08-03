package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appeaser.sublimepickerlibrary.datepicker.SelectedDate;
import com.appeaser.sublimepickerlibrary.recurrencepicker.SublimeRecurrencePicker;
import com.bartoszlipinski.recyclerviewheader2.RecyclerViewHeader;
import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.activity.StoryDetailActicity;
import com.sigaritus.swu.zhihudailym.bean.ZhihuHistoryStoryResult;
import com.sigaritus.swu.zhihudailym.bean.ZhihuStory;
import com.sigaritus.swu.zhihudailym.fragment.adapter.BaseRecyclerAdapter;
import com.sigaritus.swu.zhihudailym.fragment.adapter.HotStoryListAdapter;
import com.sigaritus.swu.zhihudailym.fragment.adapter.LatestStoryListAdapter;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.DateUtils;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class HistoryFragment extends BaseFragment {
    @Bind(R.id.history_stories)
    RecyclerView historyStory;
    @Bind(R.id.history_time_header)
    RecyclerViewHeader historyHeader;
    @Bind(R.id.history_time)
    TextView historyTime;
    LatestStoryListAdapter adapter = new LatestStoryListAdapter();
    SublimePickerFragment.Callback mFragmentCallback = new SublimePickerFragment.Callback() {
        @Override
        public void onCancelled() {
            ToastUtils.showShort("u clicked cancel");
        }

        @Override
        public void onDateTimeRecurrenceSet(SelectedDate selectedDate, int hourOfDay,
                                            int minute, SublimeRecurrencePicker.RecurrenceOption recurrenceOption, String recurrenceRule) {

            historyTime.setText(DateUtils.getDayAsString(selectedDate.getEndDate(),false));
            onLoadData(DateUtils.getDayAsReadableInt(selectedDate.getEndDate())+"");
        }
    };

    Observer<ZhihuHistoryStoryResult> observer = new Observer<ZhihuHistoryStoryResult>() {
        @Override
        public void onCompleted() {


        }

        @Override
        public void onError(Throwable e) {
            ToastUtils.showShort(e.getMessage());
        }

        @Override
        public void onNext(ZhihuHistoryStoryResult zhihuHistoryStoryResult) {
            List<ZhihuStory> storyList = zhihuHistoryStoryResult.HistoryStoryList;
            initList(storyList);
        }
    };

    private void initList(List<ZhihuStory> storyList) {

        adapter.setLaststStory(storyList);


    }

    public HistoryFragment() {
        // Required empty public constructor
    }


    public static HistoryFragment newInstance(String param1, String param2) {
        HistoryFragment fragment = new HistoryFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this,view);

        initViews();

        return view;
    }

    private void initViews() {
        int historyDate = DateUtils.getDayAsReadableInt((new DateUtils.DefaultCalendarThreadLocal()).get())-1;
        historyTime.setText(DateUtils.getDayAsString(new DateUtils.DefaultCalendarThreadLocal().get(),true));
        historyStory.setAdapter(adapter);
        adapter.setOnRecyclerViewListener(new BaseRecyclerAdapter.OnRecyclerItemClickListener() {
            @Override
            public void onClick(View v, String data) {
                getDetail(data);
            }
        });
        historyStory.setLayoutManager(new LinearLayoutManager(getContext()));

        historyHeader.attachTo(historyStory);

        onLoadData(historyDate+"");



    }

    private void onLoadData(String s) {
        subscription = Network.getZhihuApi().getHistoryStory(s)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }


    @OnClick(R.id.history_time)
    public void clickHistoryTime(){
        SublimePickerFragment dateFragment = new SublimePickerFragment();
        dateFragment.setCallback(mFragmentCallback);
        dateFragment.setStyle(DialogFragment.STYLE_NO_TITLE,0);
        dateFragment.show(getActivity().getSupportFragmentManager(),"date picker");

    }

}
