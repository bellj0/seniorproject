package server.network;

import server.network.connections.ServerChannelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 *
 * @author Josh
 *
 * @info The network server handling connection requests.
 *
 */
public class NetworkServer {

    /**
     * The boss even loop group.
     */
    private static EventLoopGroup bossGroup = new NioEventLoopGroup();

    /**
     * The worker event loop group.
     */
    private static EventLoopGroup workerGroup = new NioEventLoopGroup();

    /**
     * Attempts to connect the server to a specified port number.
     *
     * @param port The network port number.
     *
     * @throws InterruptedException Thrown when an interruption occurs in the
     * connection listener.
     */
    public void connect(int port) throws InterruptedException {
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workerGroup);
        bootstrap.channel(NioServerSocketChannel.class);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            public void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline().addLast("object-encoder", new ObjectEncoder());
                channel.pipeline().addLast("object-decoder", new ObjectDecoder(ClassResolvers.cacheDisabled(null)));
                channel.pipeline().addLast("channel-handler", new ServerChannelHandler());
            }
        });
        bootstrap.option(ChannelOption.SO_BACKLOG, 128);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

        ChannelFuture future = bootstrap.bind(port);
        future.sync();
        Channel channel = future.channel();
        channel.closeFuture();
        future.sync();
        System.out.println("Server bound to port " + port + ". Waiting for connections.");
        
    }

    /**
     * Disposes the network server.
     */
    public void dispose() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

}
