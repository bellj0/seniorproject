package server.network.transfer;

import server.user.User;
import server.user.UserRepository;
import shared.TransferableObject;
import shared.connection.Connection;
import shared.impl.AuthenticationRequest;
import shared.impl.AuthenticationResponse;

/**
 * @author Stephen Asbury
 * @author Josh Bell
 * 
 * Handles a login request, and adjusts usernames if duplicate
 *
 */
public class LoginHandler {

    /**
     * Handles a login requests, and adjusts usernames if duplicate
     */
    public static User handleLoginRequest(Connection connection, TransferableObject transferable) {
        if (transferable == null || !(transferable instanceof AuthenticationRequest)) {
            return null;
        }

        AuthenticationRequest authentication = (AuthenticationRequest) transferable;
        
		// used to see if the username requested is already in use.
		Boolean nameInUse = false;
        User user = new User(connection, authentication.getUsername());
        
        if (UserRepository.nameTaken(user.toString())) {
            nameInUse = true; 
            connection.send(new AuthenticationResponse("This name is already in use."));
            int counter = 0;
            do{
                counter++;
            }while(UserRepository.nameTaken(user.toString() + '_' + counter));
			// if name is in use, adds counter to end of name to eliminate duplicates
            user.setUsername(user.toString() + '_' + counter);
        }
        
        
        // once the username is unique, adds user to server.
        UserRepository.getUsers().add(user);
        connection.send(new AuthenticationResponse(null));
        UserRepository.updateUserList();
        if(nameInUse){
            UserRepository.message(null, user, "Welcome to the Chat Client. "
                    + "Unfortunately your username was taken.\nYour current "
                    + "username is " + user.toString() + ".");
        }
        else
        {
            UserRepository.message(null, user, "Welcome to the Chat Client, " + user.toString() + ".");
        }
        return user;
    }

}
