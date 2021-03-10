package com.example.demo.websocket;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Classname WebSocket
 * @Description TODO
 * @Date 2021/3/9 15:29
 * @Created by Administrator
 */
@ServerEndpoint("/websocket/{pageCode}")
@Component
public class WebSocket {

    private static final String loggerName=WebSocket.class.getName();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    public static Map<String, List<Session>> electricSocketMap = new ConcurrentHashMap<String, List<Session>>();

    /**
     * 连接建立成功调用的方法
     *
     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    @OnOpen
    public void onOpen(@PathParam("pageCode") String pageCode, Session session) {
        List<Session> sessions = electricSocketMap.get(pageCode);
        if(null==sessions){
            List<Session> sessionList = new ArrayList<>();
            sessionList.add(session);
            electricSocketMap.put(pageCode,sessionList);
        }else{
            sessions.add(session);
        }
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(@PathParam("pageCode") String pageCode,Session session) {
        if (electricSocketMap.containsKey(pageCode)){
            electricSocketMap.get(pageCode).remove(session);
        }
    }

    /**
     * 收到客户端消息后调用的方法
     *
     * @param object 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String object, Session session) throws IOException {
        JSONObject jsonObject = JSONObject.parseObject(object);
        String toPageCode = jsonObject.getString("toPageCode");

        if(!StringUtils.isEmpty(toPageCode)){
            for (String item : electricSocketMap.keySet()) {
                if(toPageCode.equals(item)){
                    List<Session> sessionList = electricSocketMap.get(item);
                    for(Session se : sessionList){
                        se.getBasicRemote().sendText(object);
                    }
                }
            }
        }
    }

    /**
     * 发生错误时调用
     *
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("发生错误");;
    }

    /**
     * 实现服务器主动推送
     */
    public void sendMessage(Object obj)  {
        /*try {
            synchronized (this.session) {
                this.session.getBasicRemote().sendText((JSON.toJSONString(obj)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
