package client.Controller;

import client.network.NetworkClient;
import client.network.connections.ConnectionHandler;
import client.ui.ChatConfig;
import client.ui.FrameHandler;
import java.util.Scanner;

/**
 *
 * @author Stephen Asbury
 * @author Josh Bell
 */
public class Controller {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args)
    {
        
        String host = "";
        Integer port = -1;
        //Read host and port information
        System.out.println("Please enter the host: ");
        host = sc.nextLine();
        //Empty host
        while(!ChatConfig.isValidServer(host))
        {
            System.out.println("Please enter a non-empty host: ");
            host = sc.nextLine();
        }
        System.out.println("Please enter the port (1-65535): ");
        try {
            port = Integer.parseInt(sc.nextLine());
        } catch (Exception e) {

        }
        //Port outside of range
        while(!ChatConfig.isValidPort(port)) {
            System.out.println("Please enter a port between 1-65535: ");
            try {
                port = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                
            }
        }
        //Attempt server connection
        ChatConfig config = new ChatConfig();
        //Setup window location and host/port info
        config.setup(host, port);
        FrameHandler.launchFrame(config);
        connectToServer(host, port);
        
    }
    public static void connectToServer(String host, Integer port) {
            try {
                new Thread(new NetworkClient(ChatConfig.getHost(), ChatConfig.getPort())).run();
            } catch (Exception e) {
                ConnectionHandler.disconnected();
            }
	}
}
