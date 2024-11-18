package src.server.methods.shipmethods;

import java.util.ArrayList;
import java.util.List;

import src.server.models.Ship;

/*
 * Lớp ShipService chứa các phương thức xử lý liên quan đến tàu, tạm thời cứ kệ mẹ nó đã, nếu m thích thì m phát triển sau
 * Lưu ý tàu sẽ có 5 loại như sau:
 * - Destroyer: kích thước 2, kí hiệu là D
 * - Submarine: kích thước 3, kí hiệu là S
 * - Cruiser: kích thước 3, kí hiệu là C
 * - Battleship: kích thước 4, kí hiệu là B
 * - Aircraft Carrier: kích thước 5, kí hiệu là A
 * Nếu m code thì có thể thêm phương thức để nhận biết loại tàu, ví dụ như client gửi về là "D" thì tàu đó là Destroyer
 */
public class ShipService {
    private static final int BOARD_SIZE = 10;

    public Ship createShip(char type, char direction) {
        int size;
        switch (type) {
            case 'D':
                size = 2;  // Destroyer
                break;
            case 'S':
                size = 3;  // Submarine
                break;
            case 'C':
                size = 3;  // Cruiser
                break;
            case 'B':
                size = 4;  // Battleship
                break;
            case 'A':
                size = 5;  // Aircraft Carrier
                break;
            default:
                throw new IllegalArgumentException("Invalid ship type.");
        }
        return new Ship(size, type, direction);
    }

    // Method to calculate and set the coordinates for the ship on the board
    public boolean placeShip(Ship ship, int startX, int startY, boolean[][] board) {
        int size = ship.getSize();
        char direction = ship.getDirection();
        List<int[]> coordinates = new ArrayList<>();

        // Calculate coordinates based on direction
        for (int i = 0; i < size; i++) {
            int x = startX + (direction == 'H' ? 0 : i);
            int y = startY + (direction == 'H' ? i : 0);

            // Check if the placement is within bounds and if the cell is unoccupied
            if (x < 0 || x >= BOARD_SIZE || y < 0 || y >= BOARD_SIZE || board[x][y]) {
                return false; // Placement is invalid
            }
            coordinates.add(new int[]{x, y});
        }

        // Set ship coordinates and mark board as occupied
        ship.setCoordinates(coordinates);
        for (int[] coord : coordinates) {
            board[coord[0]][coord[1]] = true;
        }

        return true; // Placement is successful
    }
}