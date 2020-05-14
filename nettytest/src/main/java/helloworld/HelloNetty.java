package helloworld;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author dingchenchen
 * @since 2020-05-14
 */
public class HelloNetty {

    private static int port = 8888;

    public static void main(String[] args) throws InterruptedException {
        //负责连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //负责具体的事务
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new MySeverInitializer());
        ChannelFuture future = bootstrap.bind(port).sync().channel().closeFuture().sync();
    }
}
