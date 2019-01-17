package com.example.myvolley;

import android.os.SystemClock;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BasicNetwork implements Network {
    private static final int DEFAULT_SIZE_LIMIT = 4096;

    private HttpStack mHttpStack;
    private ByteArrayPool mPool;

    public BasicNetwork(HttpStack stack) {
        this(stack, new ByteArrayPool(DEFAULT_SIZE_LIMIT));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool pool) {
        mHttpStack = httpStack;
        mPool = pool;
    }

    @Override
    public NetworkResponse performRequest(Request<?> request) {
        long requestStart = SystemClock.elapsedRealtime();

        while (true) {
            HttpResponse httpResponse;
            byte[] responseContents;
            Map<String, String> responseHeaders;

            try {
                Map<String, String> headers = new HashMap<>();
                addCacheHeaders(headers, request.getCacheEntry());
                httpResponse = mHttpStack.performRequest(request, headers);
                StatusLine statusLine = httpResponse.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                responseHeaders = convertHeaders(httpResponse.getAllHeaders());
                if (httpResponse.getEntity() != null) {
                    responseContents = entityToBytes(httpResponse.getEntity());
                } else {
                    responseContents = new byte[0];
                }

                return new NetworkResponse(statusCode, responseContents, responseHeaders, false, SystemClock.elapsedRealtime() - requestStart);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] entityToBytes(HttpEntity entity) {
        PoolingByteArrayOutputStream bytes = new PoolingByteArrayOutputStream(mPool, (int) entity.getContentLength());
        byte[] buffer = null;
        try {
            InputStream in = entity.getContent();
            if (in == null) {
                throw new Exception("");
            }
            buffer = mPool.getBuf(1024);

        } catch (Exception e) {

        }

        return null;
    }

    private Map<String,String> convertHeaders(Header[] headers) {
        Map<String, String> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headers.length; i++) {
            result.put(headers[i].getName(), headers[i].getValue());
        }
        return result;
    }

    private void addCacheHeaders(Map<String, String> headers, Cache.Entry cacheEntry) {

    }
}
