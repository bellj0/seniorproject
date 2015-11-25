
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

    private Boolean op = false;
    
    /**
     * The constructor of a user, which contains the connection the user
	 * is using to communicate over the network as well as the username.
     */
    public User(Connection connection, String username) {
        this.connection = connection;
        setUsername(username);
        if(UserRepository.getUsers().isEmpty())
            this.op = true;
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
	
    public String getUsername()
    {
        return username;
    }
    
    /**
     * Used to adjust username if need be.
     */
    public final void setUsername(String username){
        username = username.replace("(OP)", "");
        this.username = username.trim();
    }

    public void setOp(Boolean op)
    {
        this.op = op;
    }
    
    public Boolean getOp()
    {
        return op;
    }
    
    @Override
    public String toString() {
        if(op)
            return username + " (OP)";
        else
            return username;
    }

}
