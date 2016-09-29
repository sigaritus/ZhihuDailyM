package com.sigaritus.swu.zhihudailym.presenter;

/**
 * Created by Administrator on 2016/9/29.
 */
public interface Presenter<V> {

    void onCreate(V view);

    void onViewCreated(boolean ifRecycle);

    void onViewDestoryed(V view);

    void onDestory(V view);

}
