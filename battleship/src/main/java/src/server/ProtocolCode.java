package src.server;

/*
 * Class này cung cấp các mã giao thức cho client giao tiếp với server cũng như phản hồi của server
 * và ngược lại
 */
public class ProtocolCode {
    // Mã request từ client
    // Các thao tác liên quan đến người chơi
    public static final String LOGIN = "1001";
    public static final String REGISTER = "1002";
    public static final String LOGOUT = "1003";

    // Các thao tác liên quan đến game
    // Room protocol code
    public static final String CREATE_ROOM = "110";
    public static final String GET_ROOM_LIST = "111";
    public static final String JOIN_ROOM = "120";
    public static final String READY = "121";
    public static final String LEAVE_ROOM = "122";
    // Game protocol code
    public static final String START_GAME = "130";
    public static final String INIT_BOARD = "131";
    public static final String PLACE_SHIP = "132";
    public static final String SURRENDER = "133";
    public static final String MOVE_SHIP = "141";
    public static final String SHOOT = "142";
    public static final String CHAT = "143";

    // Mã phản hồi từ server
    public static final String LOGIN_SUCCESS = "201";
    public static final String REGISTER_SUCCESS = "202";

    // Mã lỗi
    public static final String LOGIN_FAIL = "401";
    public static final String REGISTER_FAIL = "402";
    public static final String NOT_FOUND = "404";
}
