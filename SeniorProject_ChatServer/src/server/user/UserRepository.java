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
        //Command from user
        if(message.startsWith("/"))
        {
            if(sender.getOp())
            {
                if(message.contains("/op"))
                {
                    String [] split = message.split(" ");
                    if(split.length == 2)
                    {
                        for(User u : UserRepository.getUsers())
                        {
                            if(u.getUsername().equals(split[1]))
                            {
                                if(!u.getOp())
                                {
                                    u.setOp(true);
                                    message(null, sender, "User '"+u+"' opped.");
                                    message(null, u, "User '"+sender+" opped you.");
                                }
                            }
                        }
                    }
                    else
                    {
                        message(null, sender, "No user entered.");
                    }
                    UserRepository.updateUserList();
                }
                else if(message.contains("/kick"))
                {
                    String [] split = message.split(" ");
                    if(split.length == 2)
                    {
                        for(User u : UserRepository.getUsers())
                        {
                            if(u.getUsername().equals(split[1]))
                            {
                                if(u.getOp())
                                {
                                    message(null, sender, "Cannot kick another opped user.");
                                }
                                else
                                {
                                    u.logout();
                                    u.getConnection().dispose();
                                }
                            }
                        }
                    }
                    else
                    {
                        message(null, sender, "No user entered.");
                    }
                }
                else if(message.contains("/deop"))
                {
                    String [] split = message.split(" ");
                    if(split.length == 2)
                    {
                        for(User u : UserRepository.getUsers())
                        {
                            if(u.getUsername().equals(split[1]))
                            {
                                if(u.getOp())
                                {
                                    u.setOp(false);
                                    message(null, sender, "User '"+u+"' deoped.");
                                    message(null, u, "User '"+sender+"' deopped you.");
                                }
                            }
                        }
                    }
                    else
                    {
                        message(null, sender, "No user entered.");
                    }
                    UserRepository.updateUserList();
                }
                else if(message.contentEquals("/?") || message.contentEquals("/help"))
                {
                    message(null, sender, "Valid commands: /op, /kick, /deop");
                }
                else
                {
                    message(null, sender, "Invalid command, please type /? or /help for a list of commands.");
                }
            }
            else
            {
                message(null, sender, "Must be oped to use commands.");
            }
        }
        else
        {
            if(!message.equals(""))
                for (User user : users) {
                    message(sender, user, message);
                }
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
            if (user.getUsername().equalsIgnoreCase(name)) {
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
