package helloworld;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author dingchenchen
 * @since 2020-05-14
 */
public class MySeverInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        //获取pipeline
        ChannelPipeline pipeline = socketChannel.pipeline();
        //以"/n/r"为结尾进行一个分割，主要是为了解决粘包问题
        pipeline.addLast("framer", new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()));
        pipeline.addLast("decoder", new StringDecoder());
        pipeline.addLast("encoder", new StringEncoder());
        pipeline.addLast("handler", new MyHandler());
    }
}
