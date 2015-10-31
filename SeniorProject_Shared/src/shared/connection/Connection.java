package shared.connection;

import io.netty.channel.ChannelHandlerContext;
import java.io.Serializable;
import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class Connection 
{
    private final ChannelHandlerContext context;
    public Connection(ChannelHandlerContext context) 
    {
        this.context = context;
    }
    
    public void send(TransferableObject transferable)
    {
        if(context == null || transferable == null)
        {
            System.out.println("Invalid context/transferable object");
        }
        else
        {
            for(Transferable transfer : Transferable.values()) 
            {
                if(transferable.getTransfer() == transfer) 
                {
                    if(!(transferable instanceof Serializable))
                    {
                        System.out.println("The transferable object is not serializable.");
                    }
                    else
                    {
                        context.writeAndFlush(transferable);
                        return;
                    }
                }
            }
            dispose();
        }
        
    }
    
    public void dispose()
    {
        if(context != null)
        {
            context.disconnect();
            context.close();
        }
    }
}
