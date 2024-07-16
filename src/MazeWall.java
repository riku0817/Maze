
public class MazeWall {
	int isWall; // 0 = 通路,1 = 壁
	int wallData;// 0 = 初期壁 1.. = 各生成された壁	

	public MazeWall(int isWall, int wallData) {
		this.isWall = isWall;
		this.wallData = wallData;
	}
}
