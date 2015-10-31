package shared.impl;

import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
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
