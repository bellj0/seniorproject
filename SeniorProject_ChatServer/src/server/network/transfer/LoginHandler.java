package server.network.transfer;

import server.user.User;
import server.user.UserRepository;
import shared.TransferableObject;
import shared.connection.Connection;
import shared.impl.AuthenticationRequest;
import shared.impl.AuthenticationResponse;

/**
 *
 * @author Joshua
 *
 * @info Handles a login request.
 *
 */
public class LoginHandler {

    /**
     * Handles a login request.
     *
     * @param connection The connection requesting a login.
     *
     * @param transferable The received transferable.
     */
    public static User handleLoginRequest(Connection connection, TransferableObject transferable) {
        if (transferable == null || !(transferable instanceof AuthenticationRequest)) {
            return null;
        }

        AuthenticationRequest authentication = (AuthenticationRequest) transferable;
        User user = new User(connection, authentication.getUsername());

        if (UserRepository.nameTaken(user.toString())) {
            connection.send(new AuthenticationResponse("This name is already in use."));
            return null;
        }
        UserRepository.getUsers().add(user);
        connection.send(new AuthenticationResponse(null));
        UserRepository.message(null, user, "Welcome to the RQ Chat Client, " + user.toString() + ".");
        return user;
    }

}
