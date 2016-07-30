package com.sigaritus.swu.zhihudailym.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sigaritus.swu.zhihudailym.R;
import com.sigaritus.swu.zhihudailym.bean.ZhihuThemes;
import com.sigaritus.swu.zhihudailym.fragment.adapter.ThemeListAdapter;
import com.sigaritus.swu.zhihudailym.network.Network;
import com.sigaritus.swu.zhihudailym.util.ToastUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ThemeFragment extends BaseFragment {

    @Bind(R.id.themes_list)
    RecyclerView themesList;
    public ThemeFragment() {
        // Required empty public constructor
    }
    Observer<ZhihuThemes> observer = new Observer<ZhihuThemes>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(ZhihuThemes zhihuThemes) {
            ThemeListAdapter adapter = new ThemeListAdapter();
            adapter.setZhihuThemeList(zhihuThemes.zhihuThemesList);
            themesList.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
            themesList.setAdapter(adapter);

        }
    };

    // TODO: Rename and change types and number of parameters
    public static ThemeFragment newInstance(String param1, String param2) {
        ThemeFragment fragment = new ThemeFragment();

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
        View view = inflater.inflate(R.layout.fragment_theme, container, false);
        ButterKnife.bind(this,view);
        unSubscribe();
        subscription = Network.getZhihuApi().getThemes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
        return view;
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
