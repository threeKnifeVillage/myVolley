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

    abstract protected Response<T> parseNetworkResponse(NetWorkResponse response);

}
