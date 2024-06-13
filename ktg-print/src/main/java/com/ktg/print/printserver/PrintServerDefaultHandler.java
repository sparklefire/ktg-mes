package com.ktg.print.printserver;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
@ChannelHandler.Sharable
public class PrintServerDefaultHandler extends ChannelInboundHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger("PrinterServerHandler");

    /**
     * 客户端连接建立
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        logger.info("打印机客户端已连接!");
    }

    /**
     * 客户端连接移除
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        logger.info("打印机客户端已断开!");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        try{
            ByteBuf data = (ByteBuf) msg;
            logger.info("msg recived:"+data.toString(CharsetUtil.UTF_8));

            String replyMessage = "Server reply: " + data.toString(CharsetUtil.UTF_8)+"\n";
            ByteBuf replyByteBuf = ctx.alloc().buffer();
            replyByteBuf.writeBytes(replyMessage.getBytes());
            ctx.writeAndFlush(replyByteBuf);
        }catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    /**
     * 异常发生，关闭通道，移除客户端
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.channel().close();
    }
}
