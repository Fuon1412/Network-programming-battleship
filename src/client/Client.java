/*
 * Đang trong quá trình phát triển
 */
package client;
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    public static void main (String[] args) {
        try{
            ServerSocket socket = new ServerSocket(5500);
            System.out.println("Server is running on port 5500");
            Socket client = socket.accept();
            
            //send message to Server
            Scanner input = new Scanner(System.in);
            PrintWriter output = new PrintWriter(client.getOutputStream(), true);
            
            while (true) {
                System.out.print("Server: ");
                String message = input.nextLine();
                output.println(message);
                if (message.equals("exit")) {
                    break;
                }
            }
            input.close();
            client.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
