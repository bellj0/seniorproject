package shared.impl;

import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 *
 * This object is a response given from the server after a user attempts
 * to try and connect with an AuthenticationRequest. A null response means
 * "ok go" while any other response, there is an issue. (duplicate user, etc)
 *
 */
public class AuthenticationResponse extends TransferableObject
{
    private final String responseMessage;
    public AuthenticationResponse(String responseMessage)
    {
        super(Transferable.AUTHENTICATION_RESPONSE);
        this.responseMessage = responseMessage;
    }
    public String getResponseMessage()
    {
        return responseMessage;
    }
}
