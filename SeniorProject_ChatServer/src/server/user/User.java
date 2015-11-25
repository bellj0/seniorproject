
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
    private String username;

    /**
     * The connection the user is using.
     */
    private final Connection connection;

    /**
     * The constructor of a user, which contains the connection the user
	 * is using to communicate over the network as well as the username.
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
        UserRepository.updateUserList();
        connection.dispose();
    }

    /**
     * Retrieves the connection the user is using to communicate over the
     * network.
     */
    public Connection getConnection() {
        return connection;
    }
	
	/**
	 * Used to adjust username if need be.
	 */
	public void setUsername(String username){
        this.username = username;
    }

    @Override
    public String toString() {
        return username;
    }

}
