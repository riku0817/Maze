import java.util.Random;
import java.util.Vector;

public class MakeMaze extends MyFrame {
	public void run() {
		boolean testloop = true;

		MazeWall[][] mazewall = new MazeWall[15][15];
		Vector<MazeStart> mazestart = new Vector<MazeStart>();
		Vector<nextPath> nextpath = new Vector<nextPath>();
		while (testloop == true) {
			for (int i = 0; i < 15; i++) {

				for (int j = 0; j < 15; j++) {

					mazewall[i][j] = new MazeWall(0);

					if (j == 0 || j == 14 || i == 0 || i == 14) {
						mazewall[i][j] = new MazeWall(1); // 外壁
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
			// 次の進行方向を記録する配列

			Random rand = new Random();
			int num = rand.nextInt(mazestart.size()); // xyとも偶数のスタート位置を変数numに代入
			System.out.print("\n" + mazestart.get(num).x + " x,");
			System.out.println(mazestart.get(num).y + " y");

			int nowLocationX = mazestart.get(num).x;// スタート位置を現在の位置変数に代入
			int nowLocationY = mazestart.get(num).y;

			testloop = false;

			// 壁を作るテストコード------------------
			drawString("■", 30 + (mazestart.get(num).x * 16), 80 + (mazestart.get(num).y * 16), 20);
			mazewall[mazestart.get(num).x][mazestart.get(num).y].isWall = 1;
			System.out.println(mazewall[mazestart.get(num).x][mazestart.get(num).y].isWall + " isWall");
			//---------------------------------------

			if (mazewall[nowLocationX + 1][nowLocationY].isWall != 1
					&& nowLocationX + 2 != mazestart.get(num).x || nowLocationY != mazestart.get(num).y) {
				nextpath.add(new nextPath(nowLocationX + 1, nowLocationY));

			}
			if (mazewall[nowLocationX - 1][nowLocationY].isWall != 1
					&& nowLocationX - 2 != mazestart.get(num).x || nowLocationY != mazestart.get(num).y) {
				nextpath.add(new nextPath(nowLocationX - 1, nowLocationY));

			}
			if (mazewall[nowLocationX][nowLocationY + 1].isWall != 1
					&& nowLocationX != mazestart.get(num).x || nowLocationY + 2 != mazestart.get(num).y) {
				nextpath.add(new nextPath(nowLocationX, nowLocationY + 1));

			}
			if (mazewall[nowLocationX][nowLocationY - 1].isWall != 1
					&& nowLocationX != mazestart.get(num).x || nowLocationY - 2 != mazestart.get(num).y) {
				nextpath.add(new nextPath(nowLocationX, nowLocationY - 1));

			}
			int randomnext = rand.nextInt(nextpath.size());
			System.out.println(nextpath.get(randomnext).x + " nextpathX");
			System.out.println(nextpath.get(randomnext).y + " nextpathY");

		}
	}
}
