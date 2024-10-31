package src.client;

import java.io.*;
import java.net.Socket;

public class GameClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 8080;

    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    //Khởi tạo một instance của GameClient theo pattern Singleton
    private static GameClient instance;

    private GameClient() {}

    public static GameClient getInstance() {
        if (instance == null) {
            instance = new GameClient();
        }
        return instance;
    }

    // Phương thức khởi tạo kết nối tới server
    public void start() throws IOException {
        if (socket == null || socket.isClosed()) {
            socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Connected to server on port " + SERVER_PORT);
            listenForServerResponses();
        }
    }

    private void listenForServerResponses() {
        new Thread(() -> {
            try {
                String response;
                while ((response = input.readLine()) != null) {
                    System.out.println("Server response: " + response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                cleanup();
            }
        }).start();
    }

    private void cleanup() {
        try {
            if (output != null) output.close();
            if (input != null) input.close();
            if (socket != null) socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /*
     * 2 cái phương thức dưới này qua code sai logic, đáng lẽ chỉ cần 1 socket duy nhất để gửi request và nhận response
     * thì t làm thành mỗi khi chạy 1 cái sẽ tạo ra 1 socket, nghe ngu vcl =))
     */
    // Phương thức gửi request đăng nhập
    public void sendLogin(String username, String password) {
        String request = "LOGIN;" + username + ";" + password;
        output.println(request);
    }

    // Phương thức gửi request đăng ký
    public void sendRegister(String name, String username, String password) {
        String request = "REGISTER;" + name + ";" + username + ";" + password;
        output.println(request);
    }

    // Phương thức gửi request thoát game, khi này sẽ đóng socket từ client và kết thúc chương trình
    public void sendExit() {
        try {
            if (output != null) {
                output.println("EXIT");  
            }
            cleanup(); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
