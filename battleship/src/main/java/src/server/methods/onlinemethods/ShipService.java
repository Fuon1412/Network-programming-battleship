package src.server.methods.onlinemethods;

import src.server.ProtocolCode;
import com.google.gson.*;
public class ShipService {
    private static final int MAX_BOARD_SIZE = 20;
    private char[][] board;

    public String initBoard(int size) {
        if (size > MAX_BOARD_SIZE) {
            return ProtocolCode.INVALID_SIZE;
        }
        for (int i = 0; i < size; i++) {
            board[i][i] = '0';
        }
        return ProtocolCode.INIT_SUCCESS;
    }
    
    public String getBoard() {
        return new Gson().toJson(board);
    }
}
