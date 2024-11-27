package src.server.models;

import lombok.*;

/* Class room này đại diện cho một phòng chơi trên server, mỗi phòng chứa 2 người chơi
 * Room sẽ có các trạng thái như đã đầy chưa, đang chơi hay không, đồng thời người chơi
 * cũng có thể xem các người chơi hiện tại đã có trong phòng.
 */

@Getter
@Setter
public class Room {
    private int roomId;
    private String roomName;
    private boolean isFull;
    private boolean isPlaying;
    private Player host;
    private Player guest;

    public Room(int roomId, String roomName, Player player1){ 
        this.roomId = roomId;
        this.roomName = roomName;
        this.host = player1;
    }

    public boolean addPlayer(Player player2) {
        if (isFull || isPlaying) {
            return false;
        }
        this.guest = player2;
        isFull = true;
        return true;
    }

    public boolean removePlayer(Player player) {
        if (player.equals(host)) {
            host = guest;
            guest = null;
            isFull = false;
            return true;
        } else if (player.equals(guest)) {
            guest = null;
            isFull = false;
            return true;
        }
        return false;
    }
}
