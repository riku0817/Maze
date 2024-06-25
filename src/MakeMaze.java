
public class MakeMaze extends MyFrame {
	public void run() {
		MazeWall[][] mazewall = new MazeWall[15][15];
		while (true) {
			boolean isStartSearched = false;
			for (int i = 0; i < 15; i++) {

				for (int j = 0; j < 15; j++) {

					if (Math.random() < 0.5) {
						mazewall[i][j] = new MazeWall(1); // 列
					} else {
						mazewall[i][j] = new MazeWall(0);
					}

					if (j == 0 || j == 14 || i == 0 || i == 14) {
						mazewall[i][j] = new MazeWall(1); // 外壁
					}

					if (mazewall[i][j].isWall == 1) {
						drawString("■", 30 + (i * 16), 80 + (j * 16), 20);
					}
					if ((i + 1) % 2 == 0 && (j + 1) % 2 == 0) {
						System.out.print(i);
						System.out.print(",");
						System.out.print(j);
						System.out.print(" ");
					}
					if (i == 14 && j == 14) {
						isStartSearched = true;
					}

					sleep(0.01);
				}

			}
			System.out.print("\n" + isStartSearched);
			sleep(0.1);
			clear();
		}
	}
}
