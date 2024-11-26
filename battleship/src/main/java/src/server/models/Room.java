package src.server.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Room {
    private static final int MAX_PLAYERS = 2;
    private int roomId;
    private String roomName;
    private boolean isFull;
    private boolean isPlaying;
    private List<Player> players;

    public Room(int roomId, String roomName, Player player1){ 
        this.roomId = roomId;
        this.roomName = roomName;
        this.players.add(player1);
    }

    public boolean addPlayer(Player player) {
        if (isFull || isPlaying) {
            return false;
        }
        players.add(player);
        isFull = players.size() == MAX_PLAYERS;
        return true;
    }

    public boolean removePlayer(Player player) {
        if (players.remove(player)) {
            isFull = players.size() == MAX_PLAYERS;
            return true;
        }
        return false;
    }
}
