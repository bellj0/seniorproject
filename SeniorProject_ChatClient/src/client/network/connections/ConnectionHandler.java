package client.network.connections;

import client.network.NetworkClient;
import client.ui.ChatClient;
import client.ui.ChatConfig;
import client.ui.FrameHandler;
import client.ui.ServerStatus;
import shared.connection.Connection;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class ConnectionHandler implements Runnable
{
    private static Connection connection;

    public static void connected(Connection conn) {
            connection = conn;
            //Show that the server is online in the chatconfig window
            if (FrameHandler.getListener() instanceof ChatConfig)
                    ((ChatConfig)FrameHandler.getListener()).handleServerStatus(true);
	}
	
	/**
	 * Sent when the server appears to be offline.
	 */
	public static void disconnected() {
            //If chat is in client, exit since server went offline
            if(FrameHandler.getListener() instanceof ChatClient)
                System.exit(1);
            //If chat is only in config, disable connection to server and username field
            if (FrameHandler.getListener() instanceof ChatConfig)
                ((ServerStatus)FrameHandler.getListener()).handleServerStatus(false);

            connection = null;
            new Thread(new ConnectionHandler()).run();
	}

    @Override
    public void run() {
        try {
            //Keep trying to connect to given host and port
            Thread.sleep(1000);
            new Thread(new NetworkClient(ChatConfig.getHost(), ChatConfig.getPort())).run();
        } catch (Exception e) {
            this.run();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
