package WEEK4;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class TCPClientArea {
    public static void main(String[] args) throws Exception {
        Double radius;
        String area;
        System.out.println("Client is Running");
        System.out.println("Input the radius of the circle: ");
        // Getting user input
        Scanner s = new Scanner(System.in);
        radius = s.nextDouble();


        Socket clientSocket = new Socket("127.0.0.1",8020);
        // getOutpuStream returns an output stream for writing bytes to a socket.
        DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
        // getInputStream() reads bytes from this socket
        // InputStreamReader converts bytes to characters -> output is a string
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader (clientSocket.getInputStream()));
        // writeDouble converts a doulbe to bytes saved in a long
        outToServer.writeDouble(radius);
        area=inFromServer.readLine();
        System.out.println("The area for the circle with radius: "+radius+ " is:  "+area);
        clientSocket.close();
    }



}
