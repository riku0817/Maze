
public class Wall {
	int Wall;
	int wallNum; // 0 = 通路   1 = 外壁   2.. = 生成された壁 
	int x;
	int y;

	public Wall(int Wall, int wallNum, int x, int y) {
		this.Wall = Wall;
		this.wallNum = wallNum;
		this.x = x;
		this.y = y;
	}
}
