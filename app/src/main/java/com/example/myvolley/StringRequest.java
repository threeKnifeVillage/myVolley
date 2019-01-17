package com.example.myvolley;

import android.support.annotation.NonNull;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2019/1/16
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class StringRequest extends Request<String> {

    public StringRequest(String url) {
        this(METHOD.GET, url);
    }

    public StringRequest(int method, String url) {
        super(method, url);
    }


    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        return null;
    }
}
