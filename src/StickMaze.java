import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class StickMaze extends MyFrame {
	Random rand = new Random();
	ReRoll reroll = new ReRoll();

	public void run() {
		addKeyListener(reroll);
		MakeMaze();
	}

	public void MakeMaze() {

		int mazeSizeX = 25; // 迷路の縦の長さ
		int mazeSizeY = 19; // 迷路の横の長さ
		Wall[][] wall = new Wall[mazeSizeX][mazeSizeY];
		Vector<MazeStart> mazestart = new Vector<MazeStart>();// スタート位置候補を記録する配列
		int startNum;
		int startX;
		int startY;
		int nowX;
		int nowY;

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
					wall[i][j].Wall = 1;
					drawString("■", 30 + (i * 16), 80 + (j * 16), 20);
					System.out.print(i);
					System.out.print(",");
					System.out.print(j);
					System.out.print(" ");
				}

			}

		}
		// 配列中身確認用-------------------------
		System.out.println("");
		for (int i = 0; i < mazeSizeX; i++) {

			for (int j = 0; j < mazeSizeY; j++) {
				System.out.print(wall[i][j].Wall);
			}
			System.out.println("");
		}
		// ---------------------------------------

		for (int i = 0; i < mazestart.size(); i++) { // 生成開始
			if (mazestart.get(i).y == 2) {
				nowX = mazestart.get(i).x;
				nowY = mazestart.get(i).y;
				int randomnext = rand.nextInt(4);
				switch (randomnext) {
				case 0:
					nowX += 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				case 1:
					nowX -= 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				case 2:
					nowY += 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				case 3:
					nowY -= 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				}
			} else {
				nowX = mazestart.get(i).x;
				nowY = mazestart.get(i).y;
				int randomnext = rand.nextInt(3);
				switch (randomnext) {
				case 0:
					nowX += 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				case 1:
					nowX -= 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				case 2:
					nowY += 1;
					if (wall[nowX][nowY].Wall != 1) {
						wall[nowX][nowY].Wall = 1;
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
					break;
				}
			}
			System.out.println(nowX + "," + nowY);
			sleep(0.01);
		}

		Vector<MazeStart> startpoint = new Vector<MazeStart>();
		startpoint.add(new MazeStart(1, 1));
		startpoint.add(new MazeStart(1, 17));
		startpoint.add(new MazeStart(23, 1));
		startpoint.add(new MazeStart(23, 17));
		Collections.shuffle(startpoint);
		setColor(0, 0, 255);
		drawString("■", 30 + (startpoint.get(0).x * 16), 80 + (startpoint.get(0).y * 16), 20);
		setColor(255, 0, 0);
		drawString("■", 30 + (startpoint.get(1).x * 16), 80 + (startpoint.get(1).y * 16), 20);
		setColor(0, 0, 0);

		// 配列中身確認用-------------------------
		System.out.println("");
		for (int i = 0; i < mazeSizeX; i++) {

			for (int j = 0; j < mazeSizeY; j++) {
				System.out.print(wall[i][j].Wall);
			}
			System.out.println("");
		}
		// ---------------------------------------
		System.out.println(startpoint.get(0).x + "," + startpoint.get(0).y + "スタート地点");
		System.out.println(startpoint.get(1).x + "," + startpoint.get(1).y + "ゴール地点地点");

		System.out.println("\n生成終了"); // プログラムの最後
	}

	public class ReRoll implements KeyListener {

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				clear();
				System.out.println("再生成...");
				MakeMaze();

			}
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ

		}

	}
}
