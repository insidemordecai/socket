package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

    private static ServerSocket server;

    private static int port = 9999; //port for server to listen

    public static void main(String args[]) throws IOException, ClassNotFoundException {

        server = new ServerSocket(port); //new server instance
        System.out.println("WAITING FOR REQUEST \n");

        while (true) {

            Socket socket = server.accept(); //new socket instance to wait for request on the port and accept it
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); //read from socket to OIS

            String message = (String) ois.readObject(); //convert OIS to String so as to display as output
            System.out.println("Message Received: " + message);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); //create OOS for server message
            oos.writeObject("server message " + message); //write to Socket

            //close resources
            ois.close();
            oos.close();
            socket.close();

            //close loop if client sends exit
            if (message.equalsIgnoreCase("exit")) {
                break;
            }

        }
        System.out.println("\nCLOSING CONNECTION");
        //close the ServerSocket object
        server.close();
    }

}
