package src.server.methods.onlinemethods;

/*
 * Lớp RoomService chứa các phương thức xử lý liên quan đến phòng chơi
 * createRoom: tạo phòng chơi, trả về kết quả tạo phòng thành công hoặc phòng đã đầy
 * getRoomList: lấy danh sách các phòng chơi
 * getRoom: lấy thông tin phòng chơi theo id
 * removeRoom: xóa phòng chơi theo id
 * joinRoom: tham gia phòng chơi, trả về kết quả tham gia phòng thành công hoặc thất bại
 * leaveRoom: rời phòng chơi, trả về kết quả rời phòng thành công
 * startGame: bắt đầu trận đấu, trả về kết quả bắt đầu trận đấu
 */

import java.util.*;

import src.server.ProtocolCode;
import src.server.models.Player;
import src.server.models.Room;

public class RoomService {
    private static final int MAX_ROOMS = 1000;
    private int roomCounter = 0;
    private Map<Integer, Room> rooms = new HashMap<>();

    public String createRoom(String roomName, Player host) {
        int roomId;
        if (roomCounter < MAX_ROOMS) {
            roomId = roomCounter++;
        } else {
            return ProtocolCode.FULL_ROOM;
        }
        Room room = new Room(roomId, roomName, host);
        rooms.put(roomId, room);
        return ProtocolCode.CREATE_ROOM_SUCCESS;
    }

    public List<Room> getRoomList() {
        return new ArrayList<>(rooms.values());
    }

    public Room getRoom(int roomId) {
        return rooms.get(roomId);
    }

    public String removeRoom(int roomId) {
        rooms.remove(roomId);
        return ProtocolCode.REMOVE_ROOM_SUCCESS;
    }

    public String joinRoom(Player guest, int roomId) {
        Room room = rooms.get(roomId);
        if (room.addPlayer(guest)) {
            return ProtocolCode.JOIN_ROOM_SUCCESS;
        }
        return ProtocolCode.JOIN_ROOM_FAIL;
    }

    public String leaveRoom(Player player, int roomId) {
        Room room = rooms.get(roomId);
        if (room.removePlayer(player)) {
            return ProtocolCode.LEAVE_ROOM_SUCCESS;
        }
        return ProtocolCode.NOT_FOUND;
    }
    
    public String startGame(int roomId) {
        Room room = rooms.get(roomId);
        room.setPlaying(true);
        return ProtocolCode.START_GAME;
    }
}
