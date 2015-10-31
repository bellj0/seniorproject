package shared.impl;

import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen
 */
public class ChatMessage extends TransferableObject
{
    private final String message;
    public ChatMessage(String message) 
    {
        super(Transferable.CHAT_MESSAGE);
        this.message = message;
    }
    public String getMessage()
    {
        return message;
    }
}
