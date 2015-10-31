package client.network;

import client.network.connections.ClientChannelHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class NetworkClient implements Runnable
{
    private final String host;
    private final Integer port;
    public NetworkClient(String host, Integer port)
    {
        this.host = host;
        this.port = port;
    }
    
    @Override
    public void run()
    {
        EventLoopGroup group = new NioEventLoopGroup();
	    
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(
                                            new ObjectEncoder(),
                                            new ObjectDecoder(ClassResolvers.cacheDisabled(null)),
                                            new ClientChannelHandler());
                    }
            });
            bootstrap.connect(host, port).sync().channel().closeFuture().sync();
        } catch (InterruptedException e) {
                    e.printStackTrace();
            } finally {
            group.shutdownGracefully();
        }
    }
}
