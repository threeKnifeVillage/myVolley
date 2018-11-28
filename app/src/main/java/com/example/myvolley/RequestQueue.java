package com.example.myvolley;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RequestQueue {
    private static final int DEFAULT_NETWORK_THREAD_POLL_SIZE = 4;

    private final PriorityBlockingQueue<Request<?>> mNetworkQueues = new PriorityBlockingQueue<>();
    private Network mNetwork;
    private NetworkDispatcher[] mDispatchers;
    private ResponseDelivery mDelivery;

    public RequestQueue(Network network) {
        this(network, DEFAULT_NETWORK_THREAD_POLL_SIZE);
        mNetwork = network;
    }

    public RequestQueue(Network network, int threadPoolSize) {
        this(network, threadPoolSize, new ExecutorDelivery(new Handler(Looper.getMainLooper())));
    }

    public RequestQueue(Network network, int threadPoolSize, ExecutorDelivery executorDelivery) {
        mNetwork = network;
        mDispatchers = new NetworkDispatcher[DEFAULT_NETWORK_THREAD_POLL_SIZE];
        mDelivery = executorDelivery;
    }

    public <T> Request<T> add(Request<T> request) {
        mNetworkQueues.add(request);
        return request;
    }

    public void start() {
        for (int i = 0; i < mDispatchers.length; i++) {
            NetworkDispatcher networkDispatcher = new NetworkDispatcher(mNetworkQueues, mNetwork, mDelivery);
            mDispatchers[i] = networkDispatcher;
            networkDispatcher.start();
        }
    }
}
