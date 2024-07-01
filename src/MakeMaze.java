import java.util.Random;
import java.util.Vector;

public class MakeMaze extends MyFrame {
	public void run() {
		boolean testloop = true;

		MazeWall[][] mazewall = new MazeWall[15][15];
		Vector<MazeStart> mazestart = new Vector<MazeStart>();
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
					if (i % 2 == 0 && j % 2 == 0 && i != 0 && j != 0) {
						mazestart.add(new MazeStart(i, j));
						System.out.print(i);
						System.out.print(",");
						System.out.print(j);
						System.out.print(" ");
					}

				}

			}
			Random rand = new Random();
			int num = rand.nextInt(mazestart.size()); // xyとも偶数のスタート位置を変数numに代入
			System.out.println("\n" + mazestart.get(num).x);
			System.out.println("\n" + mazestart.get(num).y);
			testloop = false;

			// 壁を作るテストコード------------------
			drawString("■", 30 + (mazestart.get(num).x * 16), 80 + (mazestart.get(num).y * 16), 20);
			mazewall[mazestart.get(num).x][mazestart.get(num).y].isWall = 1;
			System.out.println(mazewall[mazestart.get(num).x][mazestart.get(num).y].isWall);
			//---------------------------------------
		}
	}
}
