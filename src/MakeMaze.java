import java.util.Random;
import java.util.Vector;

public class MakeMaze extends MyFrame {
	MazeWall[][] mazewall = new MazeWall[15][15];
	Vector<MazeStart> mazestart = new Vector<MazeStart>();// スタート位置候補を記録する配列
	Vector<nextPath> nextpath = new Vector<nextPath>();// 次の進行方向を記録する配列
	int wallNum = 0; // wallDataに入れる壁の識別番号
	int nowLocationX;
	int nowLocationY;
	int num;
	Random rand = new Random();

	public void run() {
		boolean testloop = true;

		while (testloop == true) {
			for (int i = 0; i < 15; i++) {

				for (int j = 0; j < 15; j++) {

					mazewall[i][j] = new MazeWall(0, 0);

					if (j == 0 || j == 14 || i == 0 || i == 14) {
						mazewall[i][j] = new MazeWall(1, 0); // 外壁
					}

					if (mazewall[i][j].isWall == 1) {
						drawString("■", 30 + (i * 16), 80 + (j * 16), 20);
					}
					if (i % 2 == 0 && j % 2 == 0 && i != 0 && j != 0 && i != 14 && j != 14) {
						mazestart.add(new MazeStart(i, j));
						System.out.print(i);
						System.out.print(",");
						System.out.print(j);
						System.out.print(" ");
					}

				}

			}
			testloop = false;

			wallNum++;
			num = rand.nextInt(mazestart.size()); // xyとも偶数のスタート位置を変数numに代入
			System.out.print("\n" + mazestart.get(num).x + " x,");
			System.out.println(mazestart.get(num).y + " y");
			nowLocationX = mazestart.get(num).x;// スタート位置を現在の位置変数に代入
			nowLocationY = mazestart.get(num).y;
			MazeGeneration();

		}
	}

	public void MazeGeneration() {

		boolean isWallSpawn = false;// 一度でも生成されれば次ロールのフラグが立つ

		// 壁を作るテストコード------------------
		drawString("■", 30 + (mazestart.get(num).x * 16), 80 + (mazestart.get(num).y * 16), 20);
		mazewall[mazestart.get(num).x][mazestart.get(num).y].isWall = 1;
		mazewall[mazestart.get(num).x][mazestart.get(num).y].wallData = wallNum;
		System.out.println(mazewall[mazestart.get(num).x][mazestart.get(num).y].isWall + " isWall");
		//---------------------------------------

		int nextnum = 0;

		// 現在の位置から見た4方向から移動できる位置を取得
		if (mazewall[nowLocationX + 1][nowLocationY].isWall != 1
				&& mazewall[nowLocationX + 2][nowLocationY].wallData != mazewall[mazestart.get(num).x][mazestart
						.get(num).y].wallData) {
			nextpath.add(new nextPath(nowLocationX + 1, nowLocationY));
			isWallSpawn = true;
			nextnum = 1;
		}
		if (mazewall[nowLocationX - 1][nowLocationY].isWall != 1
				&& mazewall[nowLocationX - 2][nowLocationY].wallData != mazewall[mazestart.get(num).x][mazestart
						.get(num).y].wallData) {
			nextpath.add(new nextPath(nowLocationX - 1, nowLocationY));
			isWallSpawn = true;
			nextnum = 2;
		}
		if (mazewall[nowLocationX][nowLocationY + 1].isWall != 1
				&& mazewall[nowLocationX][nowLocationY
						+ 2].wallData != mazewall[mazestart.get(num).x][mazestart.get(num).y].wallData) {
			nextpath.add(new nextPath(nowLocationX, nowLocationY + 1));
			isWallSpawn = true;
			nextnum = 3;
		}
		if (mazewall[nowLocationX][nowLocationY - 1].isWall != 1
				&& mazewall[nowLocationX][nowLocationY - 2].wallData != mazewall[mazestart.get(num).x][mazestart
						.get(num).y].wallData) {
			nextpath.add(new nextPath(nowLocationX, nowLocationY - 1));
			isWallSpawn = true;
			nextnum = 4;
		}
		//--------------------------------------------
		int randomnext = rand.nextInt(nextpath.size()); // 次の進行方向をランダムで決める
		// 壁かどうか?
		switch (randomnext) {
		case (0):
			if (mazewall[nowLocationX + 2][nowLocationY].isWall != 1) {
				isWallSpawn = false;
			}
			;
			break;
		case (1):
			if (mazewall[nowLocationX - 2][nowLocationY].isWall != 1) {
				isWallSpawn = false;
			}
			;
			break;
		case (2):
			if (mazewall[nowLocationX][nowLocationY + 2].isWall != 1) {
				isWallSpawn = false;
			}
			;
			break;
		case (3):
			if (mazewall[nowLocationX][nowLocationY - 2].isWall != 1) {
				isWallSpawn = false;
			}
			;
			break;
		}
		if (isWallSpawn == true) {

			System.out.println(nextpath.get(randomnext).x + " nextpathX");
			System.out.println(nextpath.get(randomnext).y + " nextpathY");
			nowLocationX = nextpath.get(randomnext).x;
			nowLocationY = nextpath.get(randomnext).y;

			sleep(0.1);

			drawString("■", 30 + (nowLocationX * 16), 80 + (nowLocationY * 16), 20);
			mazewall[nowLocationX][nowLocationY].isWall = 1;
			System.out.println(mazewall[nowLocationX][nowLocationY].isWall + " isWall");
		} else if (isWallSpawn == false) {
			mazestart.remove(num);
			return;
		}
		nextpath.clear();
		MazeGeneration();
	}
}
