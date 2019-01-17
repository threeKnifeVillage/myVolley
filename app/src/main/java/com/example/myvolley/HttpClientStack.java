package com.example.myvolley;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.Map;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class HttpClientStack implements HttpStack {
    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> headers) throws IOException {
        return null;
    }
}
