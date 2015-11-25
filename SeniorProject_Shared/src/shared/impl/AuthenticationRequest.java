package shared.impl;

import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 *
 * This object is used to check the username requested by the user
 * trying to connect to the server. The server will give a Authentication
 * Response based on whether that username can be used.
 *
 */
public class AuthenticationRequest extends TransferableObject
{
    private final String username;
    public AuthenticationRequest(String username)
    {
        super(Transferable.AUTHENTICATION_REQUEST);
        this.username = username;
    }
    public String getUsername()
    {
        return username;
    }
}
