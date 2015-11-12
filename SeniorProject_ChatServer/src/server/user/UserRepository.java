package server.user;

import java.util.LinkedList;
import java.util.List;
import shared.impl.ChatMessage;
import shared.impl.UserList;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class UserRepository {

    /**
     * A list of existing users.
     */
    private static List<User> users = new LinkedList<>();

    /**
     * A method used to send a message to all users on the server.
     */
    public static void messageAll(User sender, String message) {
        for (User user : users) {
            message(sender, user, message);
        }
    }

    /**
     * Sends a message to a user.
     * This method is used by messageAll repetitively.
     */
    public static void message(User sender, User user, String message) {
        user.getConnection().send(new ChatMessage((sender == null ? "" : (sender.toString() + ": ")) + message));
    }
    
	/**
	 * This method updates the userList that all of the users see throughout
	 * their connection to the server.
	 */
	public static void updateUserList() {
        List<String> userList = new LinkedList<>();
        for(User user : users) {
            userList.add(user.toString());
        }
        for(User user : users) {
            user.getConnection().send(new UserList(userList));
        }
    }

    /**
     * Gets whether a name is already in use or not.
     */
    public static boolean nameTaken(String name) {
        for (User user : users) {
            if (user.toString().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the list with the existing users.
     */
    public static List<User> getUsers() {
        return users;
    }

}
