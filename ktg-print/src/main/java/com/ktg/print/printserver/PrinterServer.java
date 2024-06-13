package com.ktg.print.printserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 基于netty的socket服务器端，与标签打印机的客户端建立长连接，实现网络远程打印
 */
@Component
public class PrinterServer {

    @Autowired
    private PrintServerChannelInitializer printServerChannelInitializer;

    @Value("${netty.server.port}")
    private int port;

    @Value("${netty.server.threadcount}")
    private int bossThreadCount;

    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private Channel serverChannel;

    PrinterServer(){
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
    }

    public void start(){
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(printServerChannelInitializer);
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            serverChannel = channelFuture.channel();
            serverChannel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public void stop(){
        if(serverChannel !=null){
            serverChannel.close();
            serverChannel = null;
        }
    }

}
