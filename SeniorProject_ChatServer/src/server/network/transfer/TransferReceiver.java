package server.network.transfer;

import server.user.User;
import server.user.UserRepository;
import shared.TransferableObject;
import shared.impl.ChatMessage;

/**
 *
 * @author Joshua
 * 
 * Handles server-side transfers after a successful connection has been
 * made by a user.
 *
 */
public class TransferReceiver {

    /**
     * Handles any received transferable for a user.
     */
    public static void handle(User user, TransferableObject transferable) {
        if (transferable instanceof ChatMessage) {
            UserRepository.messageAll(user, ((ChatMessage) transferable).getMessage());
        }
    }

}
