package com.example.tyaathome.retrofitstudydemo.model;

import android.text.TextUtils;

import com.example.tyaathome.retrofitstudydemo.model.net.RequestVersion;
import com.example.tyaathome.retrofitstudydemo.model.net.ResponseVersion;

/**
 * 网络包工厂
 * Created by tyaathome on 2016/4/18.
 */
public class NetFactory {
    public static BaseResponse getResponse(String key) {
        if(TextUtils.isEmpty(key)) {
            return null;
        }
        if(key.equals(RequestVersion.NAME)) {
            return new ResponseVersion();
        }
        return null;
    }
}
