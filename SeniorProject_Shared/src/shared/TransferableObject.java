package shared;

import java.io.Serializable;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 *
 * Object that utilizes the transferable enumerated type,
 * allowing to set/get/serialize an object.
 *
 */
public class TransferableObject implements Serializable
{
    private final Transferable transfer;
    public TransferableObject(Transferable transfer)
    {
        this.transfer = transfer;
    }
    public Transferable getTransfer() 
    {
        return transfer;
    }
}
