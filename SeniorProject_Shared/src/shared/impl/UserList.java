package shared.impl;

import java.util.List;
import shared.Transferable;
import shared.TransferableObject;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 *
 * A list of users that is displayed within the client, for
 * other users to see who is also on the chat server.
 *
 */
public class UserList extends TransferableObject
{
    private final List<String> users;
    public UserList(List<String> users) 
    {
        super(Transferable.USER_LIST);
        this.users = users;
    }
    public List<String> getUserList()
    {
        return users;
    }
}
