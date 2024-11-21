package src.server.methods.playermethods;

/*
 * Lớp PlayerService chứa các phương thức xử lý liên quan đến người chơi
 * registerPlayer: đăng ký người chơi, trả về kết quả true nếu đăng ký thành công, false nếu người chơi đã tồn tại
 * loginPlayer: đăng nhập người chơi, trả về thông tin người chơi nếu đăng nhập thành công, null nếu không thành công
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.*;

import src.server.models.Player;
import src.server.ProtocolCode;

public class PlayerService {
    // Code bẩn vcl :v, nhưng méo code thế này thì méo chạy được =))
    private static final Path PATH = Paths.get("");
    private final Set<Player> registeredPlayers = ConcurrentHashMap.newKeySet();
    private Set<Player> currentPlayers = ConcurrentHashMap.newKeySet();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Constructor: load thông tin người chơi từ file json
    public PlayerService() {
        loadPlayersFromJson();
    }

    public synchronized boolean registerPlayer(String name, String username, String password) {
        for (Player player : registeredPlayers) {
            if (player.getUsername().equals(username)) {
                return false;
            }
        }

        Player player = new Player();
        player.setName(name);
        player.setUsername(username);
        player.setPassword(password);
        player.setElo(1000);
        registeredPlayers.add(player);

        if (writePlayersToJson()) {
            return true;
        } else {
            registeredPlayers.remove(player);
            return false;
        }
    }

    public synchronized String loginPlayer(String username, String password) {
        // Kiểm tra xem người chơi đã đăng nhập chưa
        for (Player player : currentPlayers) {
            if (player.getUsername().equals(username)) {
                System.out.println("Player " + username + " is already logged in.");
                return ProtocolCode.ALREADY_LOGGED_IN;
            }
        }

        // Tìm kiếm trong danh sách người chơi đã đăng ký
        for (Player player : registeredPlayers) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                currentPlayers.add(player);
                return ProtocolCode.LOGIN_SUCCESS;
            }
        }
        return ProtocolCode.INVALID_CREDENTIALS;
    }

    public Player getPlayerByUsername(String username) {
        for (Player player : registeredPlayers) {
            if (player.getUsername().equals(username)) {
                return player;
            }
        }
        return null;
    }

    public synchronized boolean logoutPlayer(String username) {
        return currentPlayers.removeIf(player -> player.getUsername().equals(username));
    }

    // Load thông tin người chơi từ file json
    private boolean loadPlayersFromJson() {
        try (InputStream inputStream = getClass().getResourceAsStream("/data/players.json");
                Reader reader = new InputStreamReader(inputStream)) {
            TypeToken<Set<Player>> typeToken = new TypeToken<Set<Player>>() {
            };
            Set<Player> playersFromFile = gson.fromJson(reader, typeToken.getType());
            if (playersFromFile != null) {
                registeredPlayers.addAll(playersFromFile);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lưu thông tin người chơi vào file json
    private boolean writePlayersToJson() {
        try (Writer writer = Files.newBufferedWriter(PATH)) {
            gson.toJson(registeredPlayers, writer);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
