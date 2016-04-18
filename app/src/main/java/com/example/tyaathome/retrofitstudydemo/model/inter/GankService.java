package com.example.tyaathome.retrofitstudydemo.model.inter;

import com.example.tyaathome.retrofitstudydemo.model.BaseRequest;
import com.example.tyaathome.retrofitstudydemo.model.BaseResponse;
import com.example.tyaathome.retrofitstudydemo.model.net.ResponseRandom;

import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by tyaathome on 2016/4/15.
 */
public interface GankService {

    @GET("random/data/{category}/{count}")
    Observable<ResponseRandom> getRandom(@Path("category") String category, @Path("count") String count);

    @Multipart
    @POST("ztq30_fj_jc/service.do")
    Observable<BaseResponse> getData(@Part("p") BaseRequest request);
}
