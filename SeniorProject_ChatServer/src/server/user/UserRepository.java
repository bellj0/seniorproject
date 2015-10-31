/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.user;

import java.util.LinkedList;
import java.util.List;
import shared.impl.ChatMessage;

/**
 *
 * @author Joshua
 */
public class UserRepository {

    /**
     * A list holding the existing users.
     */
    private static List<User> users = new LinkedList<>();

    /**
     * Sends a message to all users.
     *
     * @param sender The sender of the message.
     *
     * @param message The message to display.
     */
    public static void messageAll(User sender, String message) {
        for (User user : users) {
            message(sender, user, message);
        }
    }

    /**
     * Sends a message to a user.
     *
     * @param sender The sender of the message.
     *
     * @param user The user to receive the message.
     *
     * @param message The message to display.
     */
    public static void message(User sender, User user, String message) {
        user.getConnection().send(new ChatMessage((sender == null ? "" : (sender.toString() + ": ")) + message));
    }

    /**
     * Retrieves whether a name is already in use or not.
     *
     * @param name The name.
     *
     * @return The result of the operation.
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
     * Retrieves the list with the existing users.
     *
     * @return The list of existing users.
     */
    public static List<User> getUsers() {
        return users;
    }

}
