/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.util.Scanner;
import server.network.NetworkServer;

/**
 *
 * @author Josh & Stephen
 */
public class ChatServer {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        Integer port = -1;
        System.out.println("Please enter a port number: ");

        while(port < 0 || port > 65535)
        {
            try {
                port = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                
            }
            if(port > 65535 || port < 0)
            {
                System.out.println("Please enter a port between 1-65535: ");
            }
        }
        new NetworkServer().connect(port);

       
    }

}
