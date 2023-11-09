package com.ktg.framework.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * websocket 配置
 *
 * @author ruoyi
 */
@Configuration
public class WebSocketConfig
{
    /**
     * 该Bean会自动注册使用 @ServerEndpoint 注解申明的websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter()
    {
        return new ServerEndpointExporter();
    }
}
