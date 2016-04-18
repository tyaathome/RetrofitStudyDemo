package com.example.tyaathome.retrofitstudydemo.model.net;

import com.example.tyaathome.retrofitstudydemo.model.BaseResponse;
import com.example.tyaathome.retrofitstudydemo.model.data.VersionInfo;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tyaathome on 2016/4/18.
 */
public class ResponseVersion extends BaseResponse {

    public VersionInfo info = new VersionInfo();

    @Override
    public void fildData(String json) {
        try {
            info = new VersionInfo();
            JSONObject object = new JSONObject(json);
            info.nv = object.optString("nv");
            info.file = object.optString("file");
            info.size = object.optString("size");
            info.leve = object.optString("leve");
            info.des = object.optString("des");
            info.sv = object.optString("sv");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
