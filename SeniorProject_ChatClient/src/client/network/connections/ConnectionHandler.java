package client.network.connections;

import client.network.NetworkClient;
import client.ui.ChatConfig;
import client.ui.FrameHandler;
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

            if (FrameHandler.getListener() instanceof ChatConfig)
                    ((ChatConfig)FrameHandler.getListener()).enableConnect();
    }

    public static void disconnected() {
            if (FrameHandler.getListener() instanceof ChatConfig)
                    ((ChatConfig)FrameHandler.getListener()).disableConnect();

            connection = null;
            new Thread(new ConnectionHandler()).run();
    }

    @Override
    public void run() {
            try {
                    Thread.sleep(1000);

                    new Thread(new NetworkClient(((ChatConfig)FrameHandler.getListener()).getServerTextFieldText(), ((ChatConfig)FrameHandler.getListener()).getPortTextFieldText())).run();
            } catch (Exception e) {
                    this.run();
            }
    }

    public static Connection getConnection() {
            return connection;
    }
}
