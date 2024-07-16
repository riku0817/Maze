import java.util.Random;
import java.util.Vector;

public class MakeMaze3 extends MyFrame {
	int mazeSizeX = 25; // 迷路の縦の長さ
	int mazeSizeY = 19; // 迷路の横の長さ
	Wall[][] wall = new Wall[mazeSizeX][mazeSizeY];
	Vector<MazeStart> mazestart = new Vector<MazeStart>();// スタート位置候補を記録する配列
	int startNum;
	int startX;
	int startY;
	int wallNuminc = 2;
	int nowX;
	int nowY;

	Random rand = new Random();

	public void run() {
		for (int i = 0; i < mazeSizeX; i++) {

			for (int j = 0; j < mazeSizeY; j++) {

				wall[i][j] = new Wall(0, 0, i, j);

				if (j == 0 || j == mazeSizeY - 1 || i == 0 || i == mazeSizeX - 1) {
					wall[i][j] = new Wall(1, 1, i, j); // 外壁

				}

				if (wall[i][j].Wall == 1) {
					drawString("■", 30 + (i * 16), 80 + (j * 16), 20);
				}
				if (i % 2 == 0 && j % 2 == 0 && i != 0 && j != 0 && i != mazeSizeX - 1 && j != mazeSizeY - 1) {
					mazestart.add(new MazeStart(i, j));
					System.out.print(i);
					System.out.print(",");
					System.out.print(j);
					System.out.print(" ");
				}

				//sleep(0.01);

			}

		}
		// 配列中身確認用-------------------------
		System.out.println("");
		for (int i = 0; i < mazeSizeX; i++) {

			for (int j = 0; j < mazeSizeY; j++) {
				System.out.print(wall[i][j].wallNum);
			}
			System.out.println("");
		}
		// ---------------------------------------
		while (true) {
			if (mazestart.size() == 0) { // 開始できるスタート地点がなければ終了
				break;
			}

			// 配列中身確認用-------------------------
			System.out.println("");
			for (int i = 0; i < mazeSizeX; i++) {

				for (int j = 0; j < mazeSizeY; j++) {
					System.out.print(wall[i][j].Wall + " ");
				}
				System.out.println("");
			}
			// ---------------------------------------

			System.out.println("");

			Random rand = new Random();
			startNum = rand.nextInt(mazestart.size());
			startX = mazestart.get(startNum).x;
			startY = mazestart.get(startNum).y;
			System.out.print(startX + "," + startY + "  startXY");
			nowX = startX;
			nowY = startY;

			wall[startX][startY].wallNum = wallNuminc++; // 壁ID作成
			drawString("■", 30 + (startX * 16), 80 + (startY * 16), 20);
			System.out.println(wall[startX][startY].wallNum + "wallNum");
			MazeGenerator(); // 迷路生成開始
			sleep(0.001);
			mazestart.remove(startNum);// 配列から拡張済みのスタート位置を消す

		}

		System.out.println("\n生成終了"); // プログラムの最後
	}

	public void MazeGenerator() {

		wall[startX][startY].Wall = 1; // スタートに壁を建てる

		boolean isWallgen = false;

		int randomnext = rand.nextInt(4);
		switch (randomnext) {
		case 0:

			for (int i = 0; i < 2; i++) {
				nowX += 1;
				if (wall[startX][startY].wallNum != wall[nowX][nowY].wallNum && wall[nowX][nowY].wallNum != 1) {
					wall[nowX][nowY].Wall = 1;
					drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					isWallgen = true;
				}
				if (wall[startX][startY].wallNum == 0) { // 壁を建てた際通路だったら壁IDを上書き
					wall[nowX][nowY].wallNum = wall[startX][startY].wallNum;
				}
			}
			break;
		case 1:

			for (int i = 0; i < 2; i++) {
				nowX -= 1;
				if (wall[startX][startY].wallNum != wall[nowX][nowY].wallNum && wall[nowX][nowY].wallNum != 1) {
					wall[nowX][nowY].Wall = 1;
					drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					isWallgen = true;
				}
				if (wall[startX][startY].wallNum == 0) { // 壁を建てた際通路だったら壁IDを上書き
					wall[nowX][nowY].wallNum = wall[startX][startY].wallNum;
				}
			}
			break;
		case 2:

			for (int i = 0; i < 2; i++) {
				nowY += 1;
				if (wall[startX][startY].wallNum != wall[nowX][nowY].wallNum && wall[nowX][nowY].wallNum != 1) {
					wall[nowX][nowY].Wall = 1;
					drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					isWallgen = true;
				}
				if (wall[startX][startY].wallNum == 0) { // 壁を建てた際通路だったら壁IDを上書き
					wall[nowX][nowY].wallNum = wall[startX][startY].wallNum;
				}
			}
			break;
		case 3:
			for (int i = 0; i < 2; i++) {
				nowY -= 1;
				if (wall[startX][startY].wallNum != wall[nowX][nowY].wallNum && wall[nowX][nowY].wallNum != 1) {
					wall[nowX][nowY].Wall = 1;
					drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					isWallgen = true;
				}
				if (wall[startX][startY].wallNum == 0) { // 壁を建てた際通路だったら壁IDを上書き
					wall[nowX][nowY].wallNum = wall[startX][startY].wallNum;
				}
			}

			break;
		}

		System.out.println(nowX + "," + nowY + "nowXY");

		if (wall[nowX][nowY].Wall == 1 && wall[startX][startY].wallNum != wall[nowX][nowY].wallNum) {
			return; // もどる
		}

		MazeGenerator();
	}
}
