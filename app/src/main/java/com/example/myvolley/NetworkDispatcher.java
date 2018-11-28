package com.example.myvolley;

import java.util.concurrent.BlockingQueue;
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
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
        Request request = null;
        while (true) {
            try {
                request = mNetworkQueues.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
                continue;
            }

            NetWorkResponse netWorkResponsee = mNetwork.performResponse(request);
            Response<?> response = request.parseNetworkResponse(netWorkResponsee);
        }
    }
}
