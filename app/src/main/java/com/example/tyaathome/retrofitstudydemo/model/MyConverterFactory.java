package com.example.tyaathome.retrofitstudydemo.model;

import android.text.TextUtils;

import com.example.tyaathome.retrofitstudydemo.model.net.RequestVersion;
import com.example.tyaathome.retrofitstudydemo.model.net.ResponseVersion;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Iterator;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * 请求和响应对象转换器
 * Created by tyaathome on 2016/4/15.
 */
public class MyConverterFactory extends Converter.Factory{

    static final MediaType MEDIA_TYPE = MediaType.parse("text/plain");

    public MyConverterFactory() {

    }

    public static MyConverterFactory create() {
        return new MyConverterFactory();
    }

    /**
     * 通过json获取bodyname(默认body中只有一个数据)
     * @param json
     * @return
     */
    private String getBodyName(String json) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> iterator = jsonObject.keys();
            while (iterator.hasNext()) {
                // 找到body JSON
                if(iterator.next().equals("b")) {
                    JSONObject bodyJSON = jsonObject.getJSONObject("b");
                    Iterator<String> bodyIterator = bodyJSON.keys();
                    // 遍历body
                    while (bodyIterator.hasNext()) {
                        // 找到第一个key则为BodyName
                        return bodyIterator.next();
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 通过bodyname获取bodyjson
     * @param json
     * @param bodyname
     * @return
     */
    private String getBodyJSON(String json, String bodyname) {
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject bodyJSON = jsonObject.getJSONObject("b").getJSONObject(bodyname);
            return bodyJSON.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 响应
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(final Type type, Annotation[] annotations, Retrofit retrofit) {
        return new Converter<ResponseBody, BaseResponse>() {
            @Override
            public BaseResponse convert(ResponseBody value) throws IOException {
                String json = value.string();
                String bodyName = getBodyName(json);
                String bodyJson = getBodyJSON(json, bodyName);
                BaseResponse response = NetFactory.getResponse(bodyName);
                if (response == null) {
                    return null;
                }
                response.fildData(bodyJson);
                return response;
            }
        };
    }

    // 请求

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[]
            methodAnnotations, Retrofit retrofit) {
        return new Converter<BaseRequest, RequestBody>() {
            @Override
            public RequestBody convert(BaseRequest value) throws IOException {
                try {
                    JSONObject object = new JSONObject();
                    JSONObject head = new JSONObject();
                    JSONObject body = new JSONObject();
                    head.put("p", "");
                    // 添加头
                    object.put("h", head);
                    // 添加body
                    body.put(value.getName(), value.toJSON());
                    object.put("b", body);
                    return RequestBody.create(MEDIA_TYPE, object.toString());

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                return null;
            }
        };
    }
}
