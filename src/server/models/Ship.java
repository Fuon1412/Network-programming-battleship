/*
 * Hoàn thành, không cần can thiệp vào phần code này nữa
 */
package server.models;

public class Ship {
    /*
     * Trong ship class se co 5 loai tau chinh la:
     * 1. Tau chien dau (Destroyer) - 2 o
     * 2. Tau khu truc (Cruiser) - 3 o
     * 3. Tau chien thuat (Battleship) - 4 o
     * 4. Tau me (Aircraft) - 5 o
     * 5. Tau ngam (Submarine) - 3 o
     * Cac loai tau nay deu co thuoc tinh giong nhau tru Submarine se khong co thong
     * tin khi bi ban chim
     */
    private int size;
    private char type;
    private char direction;
    private Coordinates coordinates;

    //Constructor khoi tao cac thuoc tinh cua tau
    public Ship(int size, char type, char direction, Coordinates coordinates) {
        this.type = type;
        this.direction = direction;
        this.coordinates = coordinates;

        switch (type) {
            case 'D': 
                this.size = 2;
                break;
            case 'C': 
                this.size = 3;
                break;
            case 'B': 
                this.size = 4;
                break;
            case 'A': 
                this.size = 5;
                break;
            case 'S':
                this.size = 3;
                break;
            default:
                throw new IllegalArgumentException("Invalid ship type: " + type);
        }
    }

    public int getSize() {
        return this.size;
    }

    public char getType() {
        return this.type;
    }

    public char getDirection() {
        return this.direction;
    }

    public Coordinates getCoordinates() {
        return this.coordinates;
    }
}
