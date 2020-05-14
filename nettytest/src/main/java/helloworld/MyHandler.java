package helloworld;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author dingchenchen
 * @since 2020-05-14
 */
public class MyHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("客户端发来的消息："+ msg);
        channelHandlerContext.writeAndFlush("消息转换为大写是："+ msg.toUpperCase());
    }
}
