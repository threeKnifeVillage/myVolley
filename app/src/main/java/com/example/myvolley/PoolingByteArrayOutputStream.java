package com.example.myvolley;

import java.io.ByteArrayOutputStream;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/12/2
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class PoolingByteArrayOutputStream extends ByteArrayOutputStream {
    private ByteArrayPool mPool;

    public PoolingByteArrayOutputStream(ByteArrayPool pool, int contentLength) {
        mPool = pool;
    }
}
