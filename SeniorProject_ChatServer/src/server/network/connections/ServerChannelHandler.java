/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.network.connections;

import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import server.network.transfer.LoginHandler;
import server.network.transfer.TransferReceiver;
import server.user.User;
import shared.Transferable;
import shared.TransferableObject;
import shared.connection.ChannelHandler;
import shared.impl.AuthenticationRequest;

/**
 *
 * @author Josh & Stephen
 */
@Sharable

public class ServerChannelHandler extends ChannelHandler {

    /**
     * The user assigned to the channel handler.
     */
    private User user;

    @Override
    public void channelInactive(ChannelHandlerContext context) {
        if (user != null) {
            user.logout();
            return;
        }
        getConnection().dispose();
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object received) throws Exception {
        if (received instanceof TransferableObject) {
            TransferableObject transferable = (TransferableObject) received;

            for (Transferable transfer : Transferable.values()) {
                if (transfer == transferable.getTransfer()) {
                    if (transferable instanceof AuthenticationRequest) {
                        user = LoginHandler.handleLoginRequest(getConnection(), transferable);

                        if (user == null) {
                            getConnection().dispose();
                        }
                        return;
                    }
                    TransferReceiver.handle(user, transferable);
                    return;
                }
            }
            getConnection().dispose();
        }
    }

}
