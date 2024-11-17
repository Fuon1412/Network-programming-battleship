// src/server/models/Ship.java

package src.server.models;

import java.util.ArrayList;
import java.util.List;

public class Ship {
    private int size;
    private char type;
    private char direction; // 'H' for horizontal, 'V' for vertical
    private List<int[]> coordinates = new ArrayList<>(); // Stores the coordinates for the ship

    public Ship(int size, char type, char direction) {
        this.size = size;
        this.type = type;
        this.direction = direction;
    }

    public int getSize() {
        return size;
    }

    public char getType() {
        return type;
    }

    public char getDirection() {
        return direction;
    }

    public List<int[]> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<int[]> coordinates) {
        this.coordinates = coordinates;
    }
}
