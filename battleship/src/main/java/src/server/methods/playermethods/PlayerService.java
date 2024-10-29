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
import java.util.*;
import java.util.concurrent.*;

import src.server.models.Player;

public class PlayerService {
    private final String PATH = "src/server/data/players.json";
    private final Set<Player> registeredPlayers = ConcurrentHashMap.newKeySet();
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
        writePlayersToJson();
        return true;
    }

    public synchronized Player loginPlayer(String username, String password) {
        for (Player player : registeredPlayers) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                return player;
            }
        }
        return null;
    }

    // Load thông tin người chơi từ file json
    private void loadPlayersFromJson() {
        try (Reader reader = new FileReader(PATH)) {
            TypeToken<Set<Player>> typeToken = new TypeToken<Set<Player>>() {
            };
            Set<Player> playersFromFile = gson.fromJson(reader, typeToken.getType());
            if (playersFromFile != null) {
                registeredPlayers.addAll(playersFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lưu thông tin người chơi vào file json
    private void writePlayersToJson() {
        try (Writer writer = new FileWriter(PATH)) {
            gson.toJson(registeredPlayers, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
