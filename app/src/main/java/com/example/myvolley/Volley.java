package com.example.myvolley;

import android.content.Context;
import android.os.Build;



/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class Volley {

    public RequestQueue newRequestQueue(Context context) {
        return newRequestQueue(context, null);
    }

    public RequestQueue newRequestQueue(Context context, HttpStack httpStack) {
        return newRequestQueue(context, null, -1);
    }

    public RequestQueue newRequestQueue(Context context, HttpStack stack, int maxDiskCacheBytes) {
        RequestQueue request;
        if (stack == null) {
            if (Build.VERSION.SDK_INT >= 9) {
                stack = new HurlStack();
            } else {
                stack = new HttpClientStack();
            }
        }

        Network network = new BasicNetwork(stack);
        request = new RequestQueue(network);
        request.start();

        return request;
    }
}
