/*
 * Hoàn thành, không cần can thiệp vào phần code này nữa
 */
package server.models;

public class Coordinates {
    /*
     * Trong class Coordinates se co 2 thuoc tinh chinh la:
     * 1. Toa do x
     * 2. Toa do y
     * Cac toa do nay se duoc gan cho cac tau tren ban co
     */
    private int xCor;
    private int yCor;

    public Coordinates() {
        this.xCor = 0;
        this.yCor = 0;
    }

    public Coordinates(int xCor, int yCor) {
        this.xCor = xCor;
        this.yCor = yCor;
    }

    public int getXCor() {
        return this.xCor;
    }

    public int getYCor() {
        return this.yCor;
    }

    //Ham tra ve toa do x va y cua tau
    public String toString() {
        return "(" + this.xCor + ", " + this.yCor + ")";
    }   
}
