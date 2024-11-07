package src.server;

import src.server.methods.playermethods.PlayerService;
import src.server.models.Player;

import java.io.*;
import java.net.*;

public class GameServer {
    private static final int PORT = 8080;
    static PlayerService playerService = new PlayerService();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running on port " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress().getHostAddress());
                new ClientHandler(clientSocket).start();
            }
        } catch (IOException e) {
            System.err.println("Could not start server on port " + PORT);
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private final Socket clientSocket;

        public ClientHandler(Socket socket) {
            this.clientSocket = socket;
        }

        @Override
        public void run() {
            try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String request;
                while ((request = input.readLine()) != null) {
                    System.out.println("Received request: " + request);
                    String[] requestParts = request.split(";");
                    String command = requestParts[0];

                    switch (command) {
                        case "REGISTER":
                            handleRegister(requestParts, output);
                            break;

                        case "LOGIN":
                            handleLogin(requestParts, output);
                            break;

                        case "EXIT":
                            System.out.println("Client disconnected.");
                            clientSocket.close();
                            return;

                        default:
                            output.println("Invalid command");
                            break;
                    }
                }
            } catch (IOException e) {
                System.err.println("Client connection error: " + e.getMessage());
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    System.err.println("Could not close client socket: " + e.getMessage());
                }
            }
        }

        private void handleRegister(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 4) {
                output.println("register failed - invalid parameters");
                return;
            }
            String name = requestParts[1];
            String username = requestParts[2];
            String password = requestParts[3];
            boolean result = playerService.registerPlayer(name, username, password);
            output.println("register " + (result ? "success" : "failed"));
        }

        private void handleLogin(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 3) {
                output.println("login failed - invalid parameters");
                return;
            }
            String username = requestParts[1];
            String password = requestParts[2];
            Player player = playerService.loginPlayer(username, password);
            if (player != null) {
                output.println("login success " + player.getName() + " " + player.getElo());
            } else {
                output.println("login failed");
            }
        }
    }
}
