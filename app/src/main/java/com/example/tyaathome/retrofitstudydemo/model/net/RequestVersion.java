package com.example.tyaathome.retrofitstudydemo.model.net;

import com.example.tyaathome.retrofitstudydemo.model.BaseRequest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by tyaathome on 2016/4/18.
 */
public class RequestVersion extends BaseRequest {

    public String soft_id = "";

    public static final String NAME = "version";

    @Override
    public JSONObject toJSON() {
        JSONObject object = new JSONObject();
        try {
            object.put("soft_id", soft_id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return object;
    }

    @Override
    public String getName() {
        return NAME;
    }
}
