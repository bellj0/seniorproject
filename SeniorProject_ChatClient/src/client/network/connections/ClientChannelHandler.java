package client.network.connections;

import client.ui.ChatClient;
import client.ui.ChatConfig;
import client.ui.FrameHandler;
import io.netty.channel.ChannelHandlerContext;
import java.io.IOException;
import shared.TransferableObject;
import shared.connection.ChannelHandler;
import shared.impl.AuthenticationResponse;
import shared.impl.ChatMessage;
import shared.impl.UserList;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class ClientChannelHandler extends ChannelHandler
{
    @Override
    public void channelInactive(ChannelHandlerContext context) {
        ConnectionHandler.disconnected();
    }
    
    @Override
    public void channelActive(ChannelHandlerContext context) {
            super.channelActive(context);
            ConnectionHandler.connected(getConnection());
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object received) throws Exception {
            if (received instanceof TransferableObject) {
                TransferableObject transferable = (TransferableObject)received;
                //Handle TransferableObject based on type
                if (transferable instanceof AuthenticationResponse)
                        ((ChatConfig)FrameHandler.getListener()).login(((AuthenticationResponse)transferable).getResponseMessage());
                else if (transferable instanceof ChatMessage)
                        ((ChatClient)FrameHandler.getListener()).appendChatText(((ChatMessage)transferable).getMessage());
                else if (transferable instanceof UserList)
                        ((ChatClient)FrameHandler.getListener()).updateUserList(((UserList)transferable).getUserList());
            }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
            if (cause instanceof IOException) {
                    ConnectionHandler.disconnected();
                    return;
            }
            super.exceptionCaught(context, cause);
    }
}
