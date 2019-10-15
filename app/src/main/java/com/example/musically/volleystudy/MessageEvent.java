package com.example.musically.volleystudy;

/**
 * <pre>
 *     author : 王磊
 *     time   : 2019/8/10
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class MessageEvent {

    private String message;

    public MessageEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
