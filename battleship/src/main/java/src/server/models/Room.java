package src.server.models;

import lombok.*;
import java.util.List;

@Getter
@Setter
public class Room {
    private int roomId;
    private String roomName;
    private boolean isFull;
    private boolean isPlaying;
    private List<Player> players;
}
