/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.user;

import shared.connection.Connection;

/**
 *
 * @author Joshua
 */
public class User {

    /**
     * The username of the user.
     */
    private final String username;

    /**
     * The connection the user is using.
     */
    private final Connection connection;

    /**
     * The constructor.
     *
     * @param connection The connection the user is on.
     *
     * @param username The username of the user.
     */
    public User(Connection connection, String username) {
        this.connection = connection;
        this.username = username;
    }

    /**
     * Logs the user out.
     */
    public void logout() {
        UserRepository.getUsers().remove(this);
        connection.dispose();
    }

    /**
     * Retrieves the connection the user is using to communicate over the
     * network.
     *
     * @return The used connection.
     */
    public Connection getConnection() {
        return connection;
    }

    @Override
    public String toString() {
        return username;
    }

}
