package WEEK4;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerArea {
    public static void main(String[] args) throws Exception {
        double clientRadious;
        String clientArea;

        ServerSocket welcomeSocket = new ServerSocket(8020);
        System.out.println("Server is running");

        while(true){
            // Accepts socket connection
            Socket connectionSocket = welcomeSocket.accept();
            // DataInputStream deals with raw bytes BufferedReader deals with characters
           DataInputStream inFromClient= new DataInputStream(new BufferedInputStream(connectionSocket.getInputStream()));
           DataOutputStream toClient = new DataOutputStream(connectionSocket.getOutputStream());
           clientRadious = inFromClient.readDouble();
           System.out.println("The radius received from the client: "+clientRadious);

           Double area = Math.PI * clientRadious * clientRadious;
           toClient.writeBytes(area.toString());
           connectionSocket.close();
           welcomeSocket.close();
        }
    }
}
