package com.ktg.framework.websocket;

import java.util.concurrent.Semaphore;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.domain.entity.SysUser;
import com.ktg.common.core.domain.model.LoginUser;
import com.ktg.common.utils.StringUtils;
import com.ktg.common.utils.spring.SpringUtils;
import com.ktg.framework.web.service.TokenService;
import com.ktg.system.domain.SysMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * websocket 消息处理
 * 
 * @author ruoyi
 */
@Component
@ServerEndpoint("/websocket/message/{token}")
public class WebSocketServer
{
    /**
     * WebSocketServer 日志控制器
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketServer.class);


    /**
     * 默认最多允许同时在线人数100
     */
    public static int socketMaxOnlineCount = 100;

    private static Semaphore socketSemaphore = new Semaphore(socketMaxOnlineCount);

    /**
     * 连接建立成功调用的方法
     */
    @OnOpen
    public void onOpen(Session session,@PathParam("token") String token) throws Exception
    {
        boolean semaphoreFlag = false;
        //身份验证
        if(!StringUtils.isNotNull(token)){
            session.close();
            return;
        }

        TokenService tokenService = SpringUtils.getBean(TokenService.class);

        LoginUser user = tokenService.getUserByToken(token);
        if(!StringUtils.isNotNull(user)){
            session.close();
            return;
        }

        // 尝试获取信号量
        semaphoreFlag = SemaphoreUtils.tryAcquire(socketSemaphore);
        if (!semaphoreFlag)
        {
            // 未获取到信号量
            LOGGER.error("\n 当前在线人数超过限制数- {}", socketMaxOnlineCount);
            WebSocketUsers.sendMessageToUserByText(session, "当前在线人数超过限制数：" + socketMaxOnlineCount);
            session.close();
        }
        else
        {
            // 添加用户
            WebSocketUsers.put(user.getUsername(), session);
            LOGGER.info("\n 建立连接 - {}", session);
            LOGGER.info("\n 当前人数 - {}", WebSocketUsers.getUsers().size());
            WebSocketUsers.sendMessageToUserByText(session, "连接成功");
        }
    }

    /**
     * 连接关闭时处理
     */
    @OnClose
    public void onClose(Session session)
    {
        LOGGER.info("\n 关闭连接 - {}", session);
        // 移除用户
        WebSocketUsers.remove(session);
        // 获取到信号量则需释放
        SemaphoreUtils.release(socketSemaphore);
    }

    /**
     * 抛出异常时处理
     */
    @OnError
    public void onError(Session session, Throwable exception) throws Exception
    {
        if (session.isOpen())
        {
            // 关闭连接
            session.close();
        }
        String sessionId = session.getId();
        LOGGER.info("\n 连接异常 - {}", sessionId);
        LOGGER.info("\n 异常信息 - {}", exception);
        // 移出用户
        WebSocketUsers.remove(session);
        // 获取到信号量则需释放
        SemaphoreUtils.release(socketSemaphore);
    }

    /**
     * 服务器接收到客户端消息时调用的方法
     */
    @OnMessage
    public void onMessage(String message, Session session)
    {
        if(!UserConstants.WEBSOCKET_HEARTBEAT.equals(message)){
            try{
                SysMessage msg = JSON.parseObject(message, new TypeReference<SysMessage>(){});
                if(StringUtils.isNotNull(msg.getRecipientName())){
                    //这里必须传递username
                    WebSocketUsers.sendMesssageToUserByName(msg.getRecipientName(),message);
                }
            }catch (Exception e){
                LOGGER.error("\n 错误的websocket信息格式 - {}", message);
            }
        }
        LOGGER.debug("\n 收到客户端发送的消息 - {}", message);
    }

}
