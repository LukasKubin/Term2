package WEEK4;
import java.net.*;
import java.util.Scanner;

public class UDPClientArea {
    public static void main(String[] args)throws Exception{
        System.out.println("The client is running");
        System.out.println("Please input the radius: ");
        Scanner s = new Scanner(System.in);
        String radius = s.next();

        DatagramSocket clientSocket = new DatagramSocket();
        InetAddress IPAddress = InetAddress.getByName("hostname");
        byte[] sendData = new byte[1024];
        byte[] receivedData = new byte[1024];
        sendData = radius.getBytes();


    }
}
