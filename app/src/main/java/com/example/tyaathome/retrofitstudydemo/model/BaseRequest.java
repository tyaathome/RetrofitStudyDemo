package com.example.tyaathome.retrofitstudydemo.model;

import org.json.JSONObject;

/**
 * 请求
 * Created by tyaathome on 2016/4/18.
 */
public abstract class BaseRequest {
    abstract public JSONObject toJSON();
    abstract public String getName();
}
