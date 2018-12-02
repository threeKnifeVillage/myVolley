package com.example.myvolley;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
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
public class HurlStack implements HttpStack {

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException {
        String url = request.getUrl();
        HashMap<String, String> map = new HashMap<>();
        map.putAll(request.getHeaders());
        map.putAll(additionalHeaders);
        URL parsedUrl = new URL(url);
        HttpURLConnection connection = openConnection(parsedUrl, request);
        for (String headerName : map.keySet()) {
            connection.addRequestProperty(headerName, map.get(headerName));
        }


        int responseCode = connection.getResponseCode();
        setConnectionParametersForRequest(connection, request);
        return null;
    }

    private void setConnectionParametersForRequest(HttpURLConnection connection, Request<?> request) throws IOException {
        switch (request.getMethod()) {
            case Request.METHOD.GET:
                connection.setRequestMethod("GET");
                break;
        }
    }

    private HttpURLConnection openConnection(URL url, Request<?> request) throws IOException {
        HttpURLConnection connection = createConnection(url);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        return connection;

    }

    private HttpURLConnection createConnection(URL url) throws IOException{
        return (HttpURLConnection) url.openConnection();
    }


}
