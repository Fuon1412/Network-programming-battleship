/*
 * Đang trong quá trình phát triển
 */
package server;
import java.net.*;
import java.util.*;
import java.io.*;

public class Server {
    private List<Room> rooms;
    public static void main(String[] args) {
        try {
            Socket server = new Socket("localhost", 5500);
            System.out.println("Connected to server on port 5500");

            // receive message from client
            BufferedReader input = new BufferedReader(new InputStreamReader(server.getInputStream()));

            while (true) {
                String message = input.readLine();
                System.out.println("Server response: " + message);
                if (message.equals("exit")) {
                    break;
                }
            }

            server.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
