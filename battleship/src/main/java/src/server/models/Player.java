package src.server.models;
/*
 * Lớp player chứa thông tin của người chơi
 * name: tên người chơi
 * username: tên đăng nhập
 * password: mật khẩu
 * elo: điểm elo
 * updateElo: cập nhật điểm elo sau mỗi trận đấu
 */
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class Player {
    private String name;
    private String username;
    private String password;
    private boolean isOnline;
    private int elo;

}
