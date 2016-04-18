package com.example.tyaathome.retrofitstudydemo.model.net;

import com.example.tyaathome.retrofitstudydemo.model.BaseResponse;
import com.example.tyaathome.retrofitstudydemo.model.data.GankDataInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyaathome on 2016/4/15.
 */
public class ResponseRandom extends BaseResponse {

    public List<GankDataInfo> dataList = new ArrayList<GankDataInfo>();

    @Override
    public void fildData(String json) {
        try {
            dataList.clear();
            JSONObject obj = new JSONObject(json);
            JSONArray ary = obj.getJSONArray("results");
            for(int i = 0; i < ary.length(); i++) {
                JSONObject temp = ary.getJSONObject(i);
                GankDataInfo info = new GankDataInfo();
                info.publishedAt = temp.optString("publishedAt");
                info.desc = temp.optString("desc");
                info._id = temp.optString("_id");
                info.createdAt = temp.optString("createdAt");
                info.type = temp.optString("type");
                info.used = temp.optString("used");
                info.url = temp.optString("url");
                info.who = temp.optString("who");
                dataList.add(info);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
