/*
 * Đang trong quá trình phát triển
 */
package server;
import java.net.*;
import java.io.*;

public class Room {
    private Socket player1;
    private Socket player2;
    private String roomName;
    private int roomId;
    private boolean isFull;
    private boolean isPlaying;
    private boolean isAvailable;

    public Room(Socket player1, String roomName) {
        this.player1 = player1;
        this.roomName = roomName;
        this.isFull = false;
        this.isPlaying = false;
        this.isAvailable = true;
    }

    public void addPlayer(Socket player2) {
        this.player2 = player2;
        this.isFull = true;
    }
    
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public void setPlaying(boolean isPlaying) {
        this.isPlaying = isPlaying;
    }


}
