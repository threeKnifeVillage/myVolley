package com.example.myvolley;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/13
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public interface Network {
    NetworkResponse performRequest(Request<?> request);
}
