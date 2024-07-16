import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Random;
import java.util.Vector;

public class Anahori extends MyFrame {
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

				wall[i][j] = new Wall(1, 0, i, j);

				if (j == 0 || j == mazeSizeY - 1 || i == 0 || i == mazeSizeX - 1) {
					wall[i][j] = new Wall(0, 1, i, j); // 外周
					setColor(100, 100, 100);
					drawString("■", 30 + (i * 16), 80 + (j * 16), 20);
				}

				if (wall[i][j].Wall == 1) {
					setColor(0, 0, 0);
					drawString("■", 30 + (i * 16), 80 + (j * 16), 20);
				}
				if (i % 2 == 1 && j % 2 == 1 && i != 0 && j != 0 && i != mazeSizeX - 1 && j != mazeSizeY - 1) {
					mazestart.add(new MazeStart(i, j));
					System.out.print(i);
					System.out.print(",");
					System.out.print(j);
					System.out.print(" ");
				}

			}

		}
		Collections.shuffle(mazestart);
		startX = mazestart.get(0).x;
		startY = mazestart.get(0).y;
		// 生成開始
		boolean isMazegen = false;
		Vector<nextPath> nextpath = new Vector<nextPath>();
		Vector<Path> path = new Vector<Path>();
		int count = 0;
		path.add(new Path(startX, startY)); // 開拓した道を登録
		while (true) {
			count++;
			wall[startX][startY].Wall = 0;
			setColor(255, 255, 255);
			drawString("■", 30 + (startX * 16), 80 + (startY * 16), 20);
			nowX = startX;
			nowY = startY;
			while (true) {

				// 次の道が通れるか検証＆登録-----------------------------------------
				if (wall[nowX + 1][nowY].Wall == 1 && wall[nowX + 2][nowY].Wall != 0) {
					nextpath.add(new nextPath(nowX + 1, nowY));
					isMazegen = true;
				}

				if (wall[nowX - 1][nowY].Wall == 1 && wall[nowX - 2][nowY].Wall != 0) {
					nextpath.add(new nextPath(nowX - 1, nowY));
					isMazegen = true;
				}

				if (wall[nowX][nowY + 1].Wall == 1 && wall[nowX][nowY + 2].Wall != 0) {
					nextpath.add(new nextPath(nowX, nowY + 1));
					isMazegen = true;
				}

				if (wall[nowX][nowY - 1].Wall == 1 && wall[nowX][nowY - 2].Wall != 0) {
					nextpath.add(new nextPath(nowX, nowY - 1));
					isMazegen = true;
				}
				if (isMazegen == false) { // すべての方向が進めなくなったらループ終了
					break;
				}
				// nextpath中身確認用-----------------
				for (int i = 0; i < nextpath.size(); i++) {
					System.out.println("\n" + nextpath.get(i).x + "," + nextpath.get(i).y);
				}
				// -----------------------------------

				Collections.shuffle(nextpath);// 次の道を決める

				if (nextpath.size() == 0) { // 次の道候補がなければループ終了
					path.remove(0);
					break;
				}
				if (nowX - nextpath.get(0).x < 0) {
					for (int i = 0; i < 2; i++) {
						nowX = nowX + 1;
						wall[nowX][nowY].Wall = 0;
						if (nowX % 2 == 1 && nowY % 2 == 1) {
							path.add(new Path(nowX, nowY)); // 開拓した道を登録
						}
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
				} else if (nowX - nextpath.get(0).x > 0) {
					for (int i = 0; i < 2; i++) {
						nowX = nowX - 1;
						wall[nowX][nowY].Wall = 0;
						if (nowX % 2 == 1 && nowY % 2 == 1) {
							path.add(new Path(nowX, nowY)); // 開拓した道を登録
						}
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
				}
				if (nowY - nextpath.get(0).y < 0) {
					for (int i = 0; i < 2; i++) {
						nowY = nowY + 1;
						wall[nowX][nowY].Wall = 0;
						if (nowX % 2 == 1 && nowY % 2 == 1) {
							path.add(new Path(nowX, nowY)); // 開拓した道を登録
						}
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
				} else if (nowY - nextpath.get(0).y > 0) {
					for (int i = 0; i < 2; i++) {
						nowY = nowY - 1;
						wall[nowX][nowY].Wall = 0;
						if (nowX % 2 == 1 && nowY % 2 == 1) {
							path.add(new Path(nowX, nowY)); // 開拓した道を登録
						}
						drawString("■", 30 + (nowX * 16), 80 + (nowY * 16), 20);
					}
				}
				System.out.print(nowX + "," + nowY + "現在地");
				sleep(0.05);
				nextpath.clear();
				setColor(0, 0, 255);

				setColor(255, 255, 255);
				System.out.println("pathSIZE" + path.size());
			}

			Collections.shuffle(path);
			if (path.size() > 0) {
				startX = path.get(0).x;
				startY = path.get(0).y;
			}
			System.out.println("試行回数" + count);
			if (path.size() == 0) { // 生成していない道がなくなれば生成終了
				break;
			}
		}
		setColor(0, 0, 255);
		drawString("■", 30 + (1 * 16), 80 + (0 * 16), 20); // スタート位置色付け
		setColor(255, 0, 0);
		drawString("■", 30 + ((mazeSizeX - 2) * 16), 80 + ((mazeSizeY - 1) * 16), 20); // スタート位置色付け
		// 配列中身確認用-------------------------
		System.out.println("");
		for (int i = 0; i < mazeSizeX; i++) {

			for (int j = 0; j < mazeSizeY; j++) {
				System.out.print(wall[i][j].Wall);
			}
			System.out.println("");
		}
		// ---------------------------------------
		System.out.println("生成終了");
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
