package com.example.tyaathome.retrofitstudydemo.service;

import android.content.Context;
import android.widget.Toast;

import com.example.tyaathome.retrofitstudydemo.model.BaseRequest;
import com.example.tyaathome.retrofitstudydemo.model.BaseResponse;
import com.example.tyaathome.retrofitstudydemo.model.MyConverterFactory;
import com.example.tyaathome.retrofitstudydemo.model.inter.GankService;
import com.example.tyaathome.retrofitstudydemo.model.inter.OnCompleted;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by tyaathome on 2016/4/15.
 */
public class AppService {
    public static AppService instance = null;

    private Retrofit mRetrofit = null;
    private GankService mGankService = null;

    public AppService() {
        init();
    }

    public static AppService getInstance() {
        if(instance == null) {
            instance = new AppService();
        }
        return instance;
    }

    private void init() {
        if(mRetrofit == null) {
            create();
        }
        if(mGankService == null) {
            mGankService = mRetrofit.create(GankService.class);
        }
    }

    public Retrofit create() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl("http://218.85.78.125:8099/")
                .addConverterFactory(MyConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return mRetrofit;
    }

    /**
     * 开始请求数据
     * @param context
     * @param request 请求数据包
     * @param onNext 数据请求完成回调
     */
    public void startRequest(final Context context, BaseRequest request, final OnCompleted onNext) {
        init();
        mGankService.getData(request)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseResponse>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(context, "onCompleted", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseResponse baseResponse) {
                        Toast.makeText(context, "onNext", Toast.LENGTH_LONG).show();
                        if(baseResponse != null) {
                            Toast.makeText(context, "success!", Toast.LENGTH_LONG).show();
                            if(onNext != null) {
                                onNext.onCompleted(baseResponse);
                            }
                        }
                    }
                });
    }
}
