package com.example.myvolley;

import java.util.concurrent.BlockingQueue;

public class NetworkDispatcher extends Thread {
    private BlockingQueue<Request<?>> mNetworkQueues;
    private Network mNetwork;
    private ResponseDelivery mDelivery;

    public NetworkDispatcher(BlockingQueue<Request<?>> networkQueues, Network network, ResponseDelivery delivery) {
        mNetworkQueues = networkQueues;
        mNetwork = network;
        mDelivery = delivery;
    }

    @Override
    public void run() {
        Request request;
        while (true) {
            try {
                request = mNetworkQueues.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }

            NetworkResponse netWorkResponse = mNetwork.performRequest(request);
            Response<?> response = request.parseNetworkResponse(netWorkResponse);
            mDelivery.postResponse(request, response);
        }
    }
}
