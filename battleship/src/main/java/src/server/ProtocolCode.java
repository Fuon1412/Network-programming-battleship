package src.server;

/*
 * Class này cung cấp các mã giao thức cho client giao tiếp với server cũng như phản hồi của server
 * và ngược lại
 */
public class ProtocolCode {
    // Mã request từ client
    // Các thao tác liên quan đến người chơi
    public static final String LOGIN = "101";
    public static final String REGISTER = "102";
    public static final String LOGOUT = "103";
    public static final String EXIT = "104";

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
    public static final String LOGOUT_SUCCESS = "203";
    public static final String INIT_SUCCESS = "211";
    public static final String CREATE_ROOM_SUCCESS = "220";
    public static final String JOIN_ROOM_SUCCESS = "221";
    public static final String REMOVE_ROOM_SUCCESS = "222";
    public static final String LEAVE_ROOM_SUCCESS = "223";

    // Mã lỗi
    // Mã lỗi 4xx: lỗi từ client
    public static final String LOGIN_FAIL = "401";
    public static final String REGISTER_FAIL = "402";
    public static final String LOGOUT_FAIL = "403";
    public static final String NOT_FOUND = "404";
    public static final String ALREADY_LOGGED_IN = "411";
    public static final String INVALID_CREDENTIALS = "412";
    public static final String INVALID_PARAMETERS = "444";
    public static final String INVALID_COMMAND = "499";

    // Mã lỗi 5xx: lỗi từ server
    public static final String UNKNOWN_ERROR = "595";
    public static final String INVALID_SIZE = "505";
    public static final String FULL_ROOM = "520";
    public static final String JOIN_ROOM_FAIL = "521";
}
