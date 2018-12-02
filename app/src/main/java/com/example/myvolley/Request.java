package com.example.myvolley;


import java.util.Collections;
import java.util.Map;

import retrofit2.http.GET;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class Request<T> {
    private int mMethod;
    private String mRedirectUrl;
    private String mUrl;

    private boolean mResponseDelivered;

    private Cache.Entry mCacheEntry;

    abstract protected Response<T> parseNetworkResponse(NetworkResponse response);

    public void markDelivered() {
        mResponseDelivered = true;
    }

    public Cache.Entry getCacheEntry() {
        return mCacheEntry;
    }

    public void setCacheEntry(Cache.Entry cacheEntry) {
        mCacheEntry = cacheEntry;
    }

    public String getUrl() {
        return mRedirectUrl != null ? mRedirectUrl : mUrl;
    }


    public Map<String, String> getHeaders() {
        return Collections.emptyMap();
    }

    public int getMethod() {
        return mMethod;
    }

    public interface METHOD {
        int GET = 0;
    }
}
