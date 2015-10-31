package server.network.transfer;

import server.user.User;
import server.user.UserRepository;
import shared.TransferableObject;
import shared.impl.ChatMessage;

/**
 *
 * @author Joshua
 *
 * @info Handles server sided transfers after a successful connection has been
 * made.
 *
 */
public class TransferReceiver {

    /**
     * Handles any received transferable for a player.
     *
     * @param user The user.
     *
     * @param transferable The received transferable.
     */
    public static void handle(User user, TransferableObject transferable) {
        if (transferable instanceof ChatMessage) {
            UserRepository.messageAll(user, ((ChatMessage) transferable).getMessage());
        }
    }

}
