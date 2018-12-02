package com.example.myvolley;

import java.io.Serializable;
import java.util.Map;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/24
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class NetworkResponse implements Serializable {

    NetworkResponse(int statusCode, byte[] responseContents, Map<String, String> responseHeaders, boolean notMofified, long networkTimeMs) {

    }
}
