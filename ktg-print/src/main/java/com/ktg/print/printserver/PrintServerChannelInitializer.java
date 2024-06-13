package com.ktg.print.printserver;

import com.ktg.print.protocol.PrintMessageProto;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PrintServerChannelInitializer extends ChannelInitializer {
    @Autowired
    private PrintServerDefaultHandler serverDefaultHandler;

    @Autowired
    private PrintClientInfoMessageHandler printClientInfoMessageHandler;

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline().addLast(new ProtobufVarint32FrameDecoder());
        ch.pipeline().addLast(new ProtobufDecoder(PrintMessageProto.PrintClientInfoMessage.getDefaultInstance()));
        ch.pipeline().addLast(new ProtobufEncoder());
        ch.pipeline().addLast(new ProtobufVarint32LengthFieldPrepender());
        ch.pipeline().addLast(printClientInfoMessageHandler);
        ch.pipeline().addLast(serverDefaultHandler);
    }
}
