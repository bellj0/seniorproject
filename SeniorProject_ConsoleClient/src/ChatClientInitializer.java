


import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslContext;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stephen
 */
public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {
  
      private static final StringDecoder DECODER = new StringDecoder();
      private static final StringEncoder ENCODER = new StringEncoder();
  
      private static final ChatClientHandler CLIENT_HANDLER = new ChatClientHandler();
  
      private final SslContext sslCtx;
  
      public ChatClientInitializer(SslContext sslCtx) {
          this.sslCtx = sslCtx;
      }
  
      @Override
      public void initChannel(SocketChannel ch) {
          ChannelPipeline pipeline = ch.pipeline();
          ch.config().setOption(ChannelOption.newInstance("User"), "test");
          if (sslCtx != null) {
              pipeline.addLast(sslCtx.newHandler(ch.alloc(), ChatClient.HOST, ChatClient.PORT));
          }
  
          // Add the text line codec combination first,
          pipeline.addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()));
          pipeline.addLast(DECODER);
          pipeline.addLast(ENCODER);
          // and then business logic.
          pipeline.addLast(CLIENT_HANDLER);
      }
    
}
