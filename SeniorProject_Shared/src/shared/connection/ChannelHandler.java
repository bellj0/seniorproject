package shared.connection;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class ChannelHandler extends ChannelHandlerAdapter
{
    private Connection connection;
    
    @Override
    public void channelActive(ChannelHandlerContext context)
    {
        if(connection == null)
        {
            this.connection = new Connection(context);
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) 
    {
        if (!(cause instanceof IOException))
        {
            cause.printStackTrace();
            if (connection != null)
                connection.dispose();
        }
    }
    
    public Connection getConnection()
    {
        return connection;
    }
}
