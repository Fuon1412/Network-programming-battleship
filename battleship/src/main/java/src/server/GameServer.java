package src.server;

/*
 * Lớp GameServer chứa phương thức main để chạy server dùng phương thức TCP/IP
 * main: chạy server và chấp nhận kết nối từ client
 * ClientHandler: lớp con của GameServer, xử lý request từ client
 * Các loại request: REGISTER, LOGIN
 * REGISTER: đăng ký người chơi, trả về kết quả đăng ký
 * LOGIN: đăng nhập người chơi, trả về thông tin người chơi nếu đăng nhập thành công
 */
import src.server.methods.playermethods.PlayerService;
import src.server.models.Player;

import java.io.*;
import java.net.*;

public class GameServer {
    private static final int PORT = 8080;
    static PlayerService playerService = new PlayerService();

    // Chạy server và chấp nhận kết nối từ client
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {
                String request = input.readLine();
                System.out.println("Received request: " + request);

                // Xử lý request từ client, có thể là đăng ký hoặc đăng nhập hay các request khác
                while(request != null){
                    String[] requestParts = request.split(";");
                    String command = requestParts[0];

                    switch(command){
                        case "REGISTER":
                            String name = requestParts[1];
                            String username = requestParts[2];
                            String password = requestParts[3];
                            boolean result = playerService.registerPlayer(name, username, password);
                            output.println("register "+ (result ? "success" : "failed"));
                            break;
                        
                        case "LOGIN":
                            String loginUsername = requestParts[1];
                            String loginPassword = requestParts[2];
                            Player player = playerService.loginPlayer(loginUsername, loginPassword);
                            if(player != null){
                                output.println("login success "+player.getName()+" "+player.getElo());
                            } else {
                                output.println("login failed");
                            }
                            break;
                        case "EXIT":
                            clientSocket.close();
                            break;
                        default:
                            output.println("Invalid command");
                            break;
                    }
                    request = input.readLine();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
