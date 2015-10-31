package shared.impl;

import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
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
