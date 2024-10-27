/*
 * Đang trong quá trình phát triển
 */
package server.models;

public class Board {
    /*
     * Trong board class se co 2 ban co chinh la:
     * 1. Ban co cua nguoi choi A
     * 2. Ban co cua nguoi choi B
     * Moi ban co se co kich thuoc size*size
     * Player co the tuy chinh size cua ban dau
     */
    private final int SIZE_LIMIT = 15;
    private final int SHIP_LIMIT = 7;
    private int size;
    private char[][] boardA = new char[size][size];
    private char[][] boardB = new char[size][size];

    // Cac bien chua so luong cac loai tau va so luong cua chung
    // Player A:
    private int numberOfAircraftA = 0;
    private int numberOfBattleshipA = 0;
    private int numberOfCruiserA = 0;
    private int numberOfDestroyerA = 0;
    private int numberOfSubmarineA = 0;
    // Player B:
    private int numberOfAircraftB = 0;
    private int numberOfBattleshipB = 0;
    private int numberOfCruiserB = 0;
    private int numberOfDestroyerB = 0;
    private int numberOfSubmarineB = 0;

    public int setSize(int size) {
        this.size = size;
        return this.size;
    }

    public boolean checkSize(int size) {
        if (size > SIZE_LIMIT) {
            return false;
        }
        return true;
    }

    public void initBoard() {
        /*
         * Khoi tao ban dau cho 2 ban co
         * Ban dau se la 1 ma tran size*size voi cac o trong
         * Moi o trong nay se co gia tri la '-'
         * O bien cua ban dau se la cac gia tri tu 'A' den 'O' voi hang ngang va tu 1
         * den 15 voi hang doc
         */
        // first column, gia tri se la cac so tu 1 den size ban co
        for (int i = 1; i < size; i++) {
            boardA[i][0] = (char) ('0' + i);
            boardB[i][0] = (char) ('0' + i);
        }

        // cac hang ngang se la cac ky tu tu 'A' den 'O' tuy vao size nguoi choi chon
        for (int i = 1; i < size; i++) {
            boardA[0][i] = (char) ('A' + i - 1);
            boardB[0][i] = (char) ('A' + i - 1);
        }

        // cac o con lai se la '-'
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                boardA[i][j] = '-';
                boardB[i][j] = '-';
            }
        }
    }
    // Ham kiem tra neu toa do cua tau khong hop le
    public boolean isValidCoordinate(Coordinates crd, Ship s) {
        int x = crd.getXCor();
        int y = crd.getYCor();

        // Kiem tra xem tau co bi vuot qua ban co khong
        if(x < 1 || x >= size || y < 1 || y >= size) {
            return false;
        }
        return true;
    }
}
