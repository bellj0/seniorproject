package server;

import java.util.Scanner;
import server.network.NetworkServer;
import server.user.User;
import server.user.UserRepository;

/**
 *
 * @author Josh & Stephen
 *
 * This class starts the ChatServer used by the clients. It requires a port
 * number between 1 and 65535.
 *
 */
public class ChatServer {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Integer port = -1; // initialization
        System.out.println("Please enter a port number: ");

        while(port < 0 || port > 65535)
        {
			// attempts to parse the port given by the user into an Integer
            try {
                port = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                
            }
            if(port > 65535 || port < 0)
            {
				// if the port number given is not within the correct range
				// the server asks them for a port # again
                System.out.println("Please enter a port between 1-65535: ");
            }
        }
		// once a valid port number is entered for the server to run on,
		// a connection is made using that port number.
        new NetworkServer().connect(port);
        String next;
        while(true)
        {
            next = sc.nextLine();
            if(next.equals("/exit"))
                System.exit(0);
            else if(next.contains("/op"))
            {
                String [] split = next.split(" ");
                if(split.length == 2)
                {
                    for(User u : UserRepository.getUsers())
                    {
                        if(u.getUsername().equals(split[1]))
                        {
                            u.setOp(true);
                            System.out.println("User '"+u+"' opped.");
                            UserRepository.message(null, u, "Server opped you.");
                        }
                    }
                }
                else
                {
                    System.out.println("No user entered.");
                }
                UserRepository.updateUserList();
            }
            else if(next.contains("/kick"))
            {
                String [] split = next.split(" ");
                if(split.length == 2)
                {
                    for(User u : UserRepository.getUsers())
                    {
                        if(u.getUsername().equals(split[1]))
                        {
                            u.logout();
                            u.getConnection().dispose();
                            System.out.println("User '"+u+"' kicked.");
                        }
                    }
                }
                else
                {
                    System.out.println("No user entered.");
                }
            }
            else if(next.contains("/deop"))
            {
                String [] split = next.split(" ");
                if(split.length == 2)
                {
                    for(User u : UserRepository.getUsers())
                    {
                        if(u.getUsername().equals(split[1]))
                        {
                            if(u.getOp())
                            {
                                u.setOp(false);
                                System.out.println("User '"+u+"' deopped.");
                                UserRepository.message(null, u, "Server deopped you.");
                            }
                        }
                    }
                }
                else
                {
                    System.out.println("No user entered.");
                }
                UserRepository.updateUserList();
            }
        }
    }

}
