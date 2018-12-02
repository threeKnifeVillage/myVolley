package com.example.myvolley;


/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public abstract class Request<T> {
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
}
