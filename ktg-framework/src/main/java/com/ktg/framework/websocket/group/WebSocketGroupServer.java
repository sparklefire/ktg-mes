package com.ktg.framework.websocket.group;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.utils.StringUtils;
import com.ktg.framework.websocket.SemaphoreUtils;
import com.ktg.system.domain.vo.NeuronVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.Semaphore;

/**
 * websocket 消息处理
 *
 * @author qiushile <qiushile@sina.com>
 * @date 2023/11/7
 */
@Component
@ServerEndpoint("/websocket/group/{group}")
public class WebSocketGroupServer {

    /**
     * 心跳消息
     */
    private final static String PING = "ping";

    private final static String PONG = "pong";

    /**
     * WebSocketServer 日志控制器
     */
    private static final Logger logger = LoggerFactory.getLogger(WebSocketGroupServer.class);

    /**
     * 默认最多允许同时在线节点数500
     */
    public static int socketMaxOnlineCount = 500;

    private static Semaphore socketSemaphore = new Semaphore(socketMaxOnlineCount);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session, @PathParam("group") String group) throws Exception {
        boolean semaphoreFlag = false;
        //身份验证 todo 增加校验
        if (StringUtils.isEmpty(group)) {
            session.close();
            return;
        }

        // 尝试获取信号量
        semaphoreFlag = SemaphoreUtils.tryAcquire(socketSemaphore);
        if (!semaphoreFlag) {
            // 未获取到信号量
            logger.error("\n 当前在线节点数超过限制数- {}", socketMaxOnlineCount);
            WebSocketGroups.sendMessageToNodeByText(session, "当前在线节点数超过限制数：" + socketMaxOnlineCount);
            session.close();
        } else {
            // 添加节点
            WebSocketGroups.put(group, session);
            logger.info("\n 建立连接 - {}", session);
            logger.info("\n 当前节点数 - {}", WebSocketGroups.getNodes().size());
            //WebSocketGroups.sendMessageToNodeByText(session, "连接成功");
        }
    }

    /**
     * 连接关闭时处理
     */
    @OnClose
    public void onClose(Session session) {
        logger.info("\n 关闭连接 - {}", session);
        // 移除节点
        WebSocketGroups.remove(session);
        // 获取到信号量则需释放
        SemaphoreUtils.release(socketSemaphore);
    }

    /**
     * 抛出异常时处理
     */
    @OnError
    public void onError(Session session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            // 关闭连接
            session.close();
        }
        String sessionId = session.getId();
        logger.info("\n 连接异常 - {}", sessionId);
        logger.info("\n 异常信息 - {}", exception);
        // 移除节点
        WebSocketGroups.remove(session);
        // 获取到信号量则需释放
        SemaphoreUtils.release(socketSemaphore);
    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        if (!UserConstants.WEBSOCKET_HEARTBEAT.equals(message)) {
            try {
                NeuronVo msg = JSON.parseObject(message, new TypeReference<NeuronVo>() {
                });
                if (StringUtils.isNotEmpty(msg.getGroup())) {
                    //这里必须传递group name
                    WebSocketGroups.sendMessageToGroupByText(msg.getGroup(), message);
                }
            } catch (Exception e) {

                WebSocketGroups.sendMessageToNodeByText(session, "\n 错误的websocket信息格式 - " + message);
                logger.error("\n 错误的websocket信息格式 - {}", message);
            }
        }
        logger.debug("\n 收到客户端发送的消息 - {}", message);
    }

    /**
     * 收到客户端消息后调用的方法
     */
    @OnMessage
    public void onMessage(byte[] bytes, Session session) {
        onMessage(new String(bytes), session);
    }
}
