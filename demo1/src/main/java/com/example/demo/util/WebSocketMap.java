package com.example.demo.util;

/**
 * @Classname WebSocketMap
 * @Description TODO
 * @Date 2021/3/9 16:05
 * @Created by Administrator
 */
public class WebSocketMap {
    private int fromId;
    private String fromMessage;
    private int toId;
    private String toMessage;

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getFromMessage() {
        return fromMessage;
    }

    public void setFromMessage(String fromMessage) {
        this.fromMessage = fromMessage;
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public String getToMessage() {
        return toMessage;
    }

    public void setToMessage(String toMessage) {
        this.toMessage = toMessage;
    }

}
