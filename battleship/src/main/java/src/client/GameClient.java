package src.client;

/*
 * Lớp client giúp các controller truyền dữ liệu nhận được từ view lên server
 * cũng như phản hồi từ server về để controller truyền lên view
 */

import java.io.*;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    //Cần khởi chạy main ở đây nữa để thực hiện việc kết nối đến server bằng giao thức TCP/IP
    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            String request = consoleInput.readLine();
            output.println(request);

            String response = input.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * Dưới đây là các phương thức để client tương tác với server
     * sendLogin: gửi thông tin đăng nhập lên server
     * sendRegister: gửi thông tin đăng ký lên server
     */
    public static void sendLogin(String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String request = "LOGIN;" + username + ";" + password;
            output.println(request);

            String response = input.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendRegister(String name, String username, String password) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String request = "REGISTER;" + name + ";" + username + ";" + password;
            output.println(request);

            String response = input.readLine();
            System.out.println("Server response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
