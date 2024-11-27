package src.server;
import src.server.methods.playermethods.PlayerService;
import src.server.methods.onlinemethods.RoomService;
import src.server.models.Player;
import src.server.models.Room;

import java.io.*;
import java.net.*;
import java.util.List;

public class GameServer {
    private static final int PORT = 8080;
    static PlayerService playerService = new PlayerService();
    static RoomService roomService = new RoomService();

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
                        case ProtocolCode.REGISTER:
                            handleRegister(requestParts, output);
                            break;

                        case ProtocolCode.LOGIN:
                            handleLogin(requestParts, output);
                            break;

                        case ProtocolCode.LOGOUT:
                            handleLogOut(requestParts, output);
                            break;

                        case ProtocolCode.CREATE_ROOM:
                            handleCreateRoom(requestParts, output);
                            break;

                        case ProtocolCode.GET_ROOM_LIST:
                            handleGetRoomList(output);
                            break;
                        
                        case ProtocolCode.JOIN_ROOM:
                            handleJoinRoom(requestParts, output);
                            break;

                        case ProtocolCode.LEAVE_ROOM:
                            // handleLeaveRoom(requestParts, output);
                            break;
                        
                        case ProtocolCode.READY:
                            // handleReady(requestParts, output);
                            break;
                        
                        case ProtocolCode.START_GAME:
                            // handleStartGame(requestParts, output);
                            break;
                        
                        case ProtocolCode.EXIT:
                            System.out.println("Client disconnected.");
                            clientSocket.close();
                            return;

                        default:
                            output.println(ProtocolCode.INVALID_COMMAND);
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
                output.println(ProtocolCode.INVALID_PARAMETERS);
                return;
            }
            String name = requestParts[1];
            String username = requestParts[2];
            String password = requestParts[3];
            boolean result = playerService.registerPlayer(name, username, password);
            output.println(result ? ProtocolCode.REGISTER_SUCCESS : ProtocolCode.REGISTER_FAIL);
        }

        private void handleLogin(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 3) {
                output.println(ProtocolCode.INVALID_PARAMETERS);
                return;
            }
            String username = requestParts[1];
            String password = requestParts[2];
            String loginResult = playerService.loginPlayer(username, password);

            switch (loginResult) {
                case ProtocolCode.LOGIN_SUCCESS:
                    Player player = playerService.getPlayerByUsername(username);
                    output.println(ProtocolCode.LOGIN_SUCCESS + " " + player.getName() + " " + player.getElo());
                    break;

                case ProtocolCode.ALREADY_LOGGED_IN: 
                    output.println(ProtocolCode.ALREADY_LOGGED_IN);
                    break;

                case ProtocolCode.INVALID_CREDENTIALS:
                    output.println(ProtocolCode.INVALID_CREDENTIALS);
                    break;

                default:
                    output.println(ProtocolCode.UNKNOWN_ERROR);
                    break;
            }
        }

        private void handleLogOut(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 2) {
                output.println(ProtocolCode.INVALID_PARAMETERS);
                return;
            }
            String username = requestParts[1];
            boolean result = playerService.logoutPlayer(username);
            output.println(result ? ProtocolCode.LOGOUT_SUCCESS : ProtocolCode.LOGOUT_FAIL);
        }

        private void handleCreateRoom(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 3) {
                output.println(ProtocolCode.INVALID_PARAMETERS);
                return;
            }
            String roomName = requestParts[1];
            String hostUsername = requestParts[2];
            Player host = playerService.getPlayerByUsername(hostUsername);
            String result = roomService.createRoom(roomName, host);
            output.println(result);
        }

        private void handleGetRoomList(PrintWriter output) {
            List<Room> rooms = roomService.getRoomList();
            output.println(rooms);
        }

        private void handleJoinRoom(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 3) {
                output.println(ProtocolCode.INVALID_PARAMETERS);
                return;
            }
            String guestUsername = requestParts[1];
            int roomId = Integer.parseInt(requestParts[2]);
            Player guest = playerService.getPlayerByUsername(guestUsername);
            String result = roomService.joinRoom(guest, roomId);
            output.println(result);
        }

        private void handleLeaveRoom(String[] requestParts, PrintWriter output) {
            if (requestParts.length < 3) {
                output.println(ProtocolCode.INVALID_PARAMETERS);
                return;
            }
            String username = requestParts[1];
            int roomId = Integer.parseInt(requestParts[2]);
            Player player = playerService.getPlayerByUsername(username);
            String result = roomService.leaveRoom(player, roomId);
            output.println(result);
        }

        
    }
}
