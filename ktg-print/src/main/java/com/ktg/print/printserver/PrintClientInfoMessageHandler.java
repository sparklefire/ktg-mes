package com.ktg.print.printserver;

import com.ktg.print.protocol.PrintMessageProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PrintClientInfoMessageHandler extends SimpleChannelInboundHandler<PrintMessageProto.PrintClientInfoMessage> {

    //接收到客户端发送的客户端信息后才会保存client信息
    private Map<String, Channel> clients = new ConcurrentHashMap<>();
    private ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, PrintMessageProto.PrintClientInfoMessage clientInfoMessag) throws Exception {
        if(!clients.containsKey(clientInfoMessag.getSid())){
            clients.put(clientInfoMessag.getSid(),channelHandlerContext.channel());
        }
        channelHandlerContext.fireChannelRead(clientInfoMessag);
        group.add(channelHandlerContext.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        group.remove(ctx.channel());
    }
}
