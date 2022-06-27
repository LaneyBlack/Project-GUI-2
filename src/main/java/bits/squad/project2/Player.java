package bits.squad.project2;

public class Player {
    private String nickname;
    private String time;
    private int gridSize;

    public Player(String nickname, String time, int gridSize) {
        this.nickname = nickname;
        this.time = time;
        this.gridSize = gridSize;
    }

    public String getNickname() {
        return nickname;
    }

    public String getTime() {
        return time;
    }

    public int getGridSize() {
        return gridSize;
    }
}
