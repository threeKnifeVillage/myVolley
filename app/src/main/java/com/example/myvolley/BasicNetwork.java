package com.example.myvolley;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class BasicNetwork implements Network {
    private HttpStack mHttpStack;

    public BasicNetwork(HttpStack stack) {
        mHttpStack = stack;
    }

    @Override
    public NetWorkResponse performRequest(Request<?> request) {
        while (true) {
            HttpResponse httpResponse = null;
            byte[] responseContents = null;
            Map<String, String> responseHeaders = Collections.emptyMap();
            try {
                Map<String, String> headers = new HashMap<>();
                addCacheHeaders(headers, request.getCacheEntry());
                httpResponse = mHttpStack.performRequest(request, headers);
                StatusLine statusLine = httpResponse.getStatusLine();
                int statusCode = statusLine.getStatusCode();

                responseHeaders = convertHeaders(httpResponse.getAllHeaders());
            } catch (Exception e) {

            }
        }
    }

    private Map<String,String> convertHeaders(Header[] allHeaders) {

    }

    private void addCacheHeaders(Map<String, String> headers, Cache.Entry cacheEntry) {

    }
}
