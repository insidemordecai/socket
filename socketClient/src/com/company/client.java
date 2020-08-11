package com.company;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException{

        InetAddress host = InetAddress.getLocalHost(); //fetch IP address if on local network
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        for(int i=0; i<4;i++){
            socket = new Socket(host.getHostName(), 9999); //make connection
            oos = new ObjectOutputStream(socket.getOutputStream()); //write to socket using OOS

            if(i==0){
                System.out.println("SENDING REQUEST \n");
            }

            if(i==3){
                oos.writeObject("exit"); //to terminate
            }
            else{
                oos.writeObject("client message ");
            }

            //read message from the server
            ois = new ObjectInputStream(socket.getInputStream());
            String message = (String) ois.readObject();
            System.out.println("Message: " + message);

            //close resources
            ois.close();
            oos.close();
            Thread.sleep(100);
        }
    }
}
