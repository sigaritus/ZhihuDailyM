package com.sigaritus.swu.zhihudailym.network;

import com.sigaritus.swu.zhihudailym.network.api.ZhihuApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/7/18.
 */
public class Network {
    private static ZhihuApi zhihuApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();

    public static ZhihuApi getZhihuApi(){
        if (zhihuApi==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(ZhihuApi.BASE_URL)
                    .addConverterFactory(gsonFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            zhihuApi = retrofit.create(ZhihuApi.class);
        }
        return zhihuApi;
    }
}
