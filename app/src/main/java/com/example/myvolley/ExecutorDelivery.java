package com.example.myvolley;

import android.os.Handler;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2018/10/14
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class ExecutorDelivery implements ResponseDelivery {
    private Executor mResponseExecutor;

    public ExecutorDelivery(final Handler handler) {
        mResponseExecutor = new Executor() {
            @Override
            public void execute(@NonNull Runnable command) {
                handler.post(command);
            }
        };
    }

    @Override
    public void postResponse(Request<?> request, Response<?> response) {
        postResponse(request, response, null);
    }

    private void postResponse(Request<?> request, Response<?> response, Runnable runnable) {
        request.markDelivered();
        mResponseExecutor.execute(new ResponseDeliveryRunnable(request, response, runnable));
    }


    private class ResponseDeliveryRunnable implements Runnable {
        private final Request mRequest;
        private final Response mResponse;
        private final Runnable mRunnable;

        public ResponseDeliveryRunnable(Request<?> request, Response<?> response, Runnable runnable) {
            mRequest = request;
            mResponse = response;
            mRunnable = runnable;
        }

        @Override
        public void run() {

        }
    }
}
